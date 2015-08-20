package com.cardinalsolutions.sectioned_adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A {@link RecyclerView.Adapter} that is capable of automatically generating section header views
 * and inserting them into a list of {@link Categorizable} data.
 * <p/>
 * <p>
 * Subclasses should implement <code>onCreateItemViewHolder()</code> to return a
 * {@link android.support.v7.widget.RecyclerView.ViewHolder} for their data object and then
 * implement <code>onBindItemViewHolder()</code> to bind the data object to the view.
 * </p>
 * <p>
 * Calling <code>setItemList()</code> with a list of <code>Categorizable</code> objects will
 * automatically generate and insert headers at the appropriate positions.  The list of items
 * does not need to be sorted.
 * </p>
 *
 * @author Alex Morgan {amorgan@cardinalsolutions.com}
 */
public abstract class SectionedAdapter<T extends Categorizable> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @IntDef({TYPE_ITEM, TYPE_SECTION_HEADER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {
    }

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SECTION_HEADER = 1;

    private List<Object> itemList;

    @LayoutRes
    private int headerLayoutResource = -1;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (this.getItemViewType(position)) {
            case TYPE_SECTION_HEADER: {
                SectionHeader header = (SectionHeader) this.itemList.get(position);
                ((SectionViewHolder) holder).titleView.setText(header.title);
                break;
            }
            case TYPE_ITEM: {
                this.onBindItemViewHolder(holder, (T) this.itemList.get(position), this.getItemViewType(position));
                break;
            }
            default: {
                throw new RuntimeException("SectionedAdapter could not bind the requested view type.");
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, @ViewType int viewType) {
        switch (viewType) {
            case TYPE_SECTION_HEADER: {
                View view;
                if (this.headerLayoutResource == -1) {
                    view = this.createStandardHeaderView(parent);
                } else {
                    view = this.createCustomHeaderView(parent, this.headerLayoutResource);
                }
                return new SectionViewHolder(view);
            }
            case TYPE_ITEM: {
                return this.onCreateItemViewHolder(parent, viewType);
            }
            default: {
                throw new RuntimeException("SectionedAdapter could not create a ViewHolder for the requested view type.");
            }
        }
    }

    @Override
    @ViewType
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof Categorizable) {
            return TYPE_ITEM;
        } else if (itemList.get(position) instanceof SectionHeader) {
            return TYPE_SECTION_HEADER;
        } else {
            throw new RuntimeException("SectionedAdapter was unable to determine the view type for the item at position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * Sets the list of items to be displayed in the adapter.  This method automatically takes care
     * of sorting the data according to category and inserts header views at the beginning of each
     * category.
     *
     * @param itemList a list of {@link Categorizable} objects to be displayed in sections
     */
    public void setItemList(List<? extends Categorizable> itemList) {
        Collections.sort(itemList, new Comparator<Categorizable>() {
            @Override
            public int compare(Categorizable lhs, Categorizable rhs) {
                return String.CASE_INSENSITIVE_ORDER.compare(lhs.getCategory(), rhs.getCategory());
            }
        });
        this.itemList = SectionGenerator.getSectionsForItems(itemList);
        this.notifyDataSetChanged();
    }

    /**
     * Sets a layout resource to inflate when creating the headers for sections.  The specified
     * layout must contain a TextView with the id <code>android.R.id.title</code> or else an
     * exception will be thrown when the ViewHolder is later bound.
     *
     * @param headerResource the resource ID
     */
    protected void setCustomHeaderLayout(@LayoutRes int headerResource) {
        this.headerLayoutResource = headerResource;
    }

    /**
     * This method is called when the adapter needs to bind data for a data object into a view holder.
     *
     * @param holder   a {@link android.support.v7.widget.RecyclerView.ViewHolder} subclass of the
     *                 same type supplied by <code>onCreateItemViewHolder()</code>
     * @param item     the data object to bind
     * @param viewType the type of view that should be bound
     */
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, T item, @ViewType int viewType);

    /**
     * This method is called to create a {@link android.support.v7.widget.RecyclerView.ViewHolder}
     * for each item in the adapter.  It should return a ViewHolder subclass suitable for binding
     * to the specified view type.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The type of the new View
     * @return A new ViewHolder that holds a View of the given view type.
     */
    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, @ViewType int viewType);

    /**
     * A ViewHolder for holding a section header's View.
     */
    private static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;

        SectionViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewWithTag("TITLE");
        }
    }

    static class SectionHeader {
        SectionHeader(String title) {
            this.title = title;
        }

        final String title;
    }

    private View createStandardHeaderView(View parent) {
        LinearLayout sectionView = new LinearLayout(parent.getContext());
        sectionView.setLayoutParams(parent.getLayoutParams());

        int horizontalPadding = this.pixelsToDp(parent.getContext(), 16);
        int verticalPadding = this.pixelsToDp(parent.getContext(), 8);
        sectionView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        TextView titleView = new TextView(parent.getContext());
        titleView.setTag("TITLE");
        titleView.setTextColor(Color.BLACK);
        sectionView.addView(titleView);
        return sectionView;
    }

    private View createCustomHeaderView(ViewGroup parent, @LayoutRes int layoutResource) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResource, parent, false);
        TextView titleView = (TextView) view.findViewById(android.R.id.title);
        if (titleView == null) {
            throw new RuntimeException("The header layout file MUST contain a TextView with the id `android.R.id.title`; unable to bind header views.");
        }
        titleView.setTag("TITLE");
        return view;
    }

    private int pixelsToDp(Context context, int pixels) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }
}
