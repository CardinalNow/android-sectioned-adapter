package com.cardinalsolutions.sectioned_adapter.demo.cats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardinalsolutions.sectioned_adapter.SectionedAdapter;
import com.cardinalsolutions.sectioned_adapter.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * An Adapter for binding Cat instances to a sectioned list where each item is sectioned by breed.
 */
public class CatAdapter extends SectionedAdapter<Cat> {

    public CatAdapter() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Snowball II", "Persian Longhair"));
        cats.add(new Cat("Felix", "Mixed"));
        cats.add(new Cat("Snowball", "Persian Longhair"));
        cats.add(new Cat("Lil' Bub", "Tabby"));
        cats.add(new Cat("Thomas O'Malley", "Alley Cat"));
        cats.add(new Cat("Si", "Siamese"));
        cats.add(new Cat("Am", "Siamese"));
        cats.add(new Cat("Garfield", "Tabby"));
        cats.add(new Cat("Puss in Boots", "Tabby"));
        cats.add(new Cat("Milo", "Tabby"));
        cats.add(new Cat("Mr. Bigglesworth", "Persian Longhair"));
        cats.add(new Cat("Grumpy Cat", "Mixed"));
        cats.add(new Cat("Crookshanks", "Himalayan"));
        cats.add(new Cat("Salem", "Black"));
        cats.add(new Cat("Church", "Undead"));
        cats.add(new Cat("Figaro", "Tuxedo Cat"));
        cats.add(new Cat("Binx", "Black"));
        cats.add(new Cat("Oliver", "Tabby"));

        this.setItemList(cats);
        this.setCustomHeaderLayout(R.layout.recycler_header_cat);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, Cat cat, int viewType) {
        CatViewHolder catViewHolder = (CatViewHolder) holder;
        catViewHolder.nameLabel.setText(cat.name);
        catViewHolder.breedLabel.setText(cat.breed);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, @ViewType int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell_cat, parent, false);
        return new CatViewHolder(view);
    }

    static class CatViewHolder extends RecyclerView.ViewHolder {
        TextView nameLabel;
        TextView breedLabel;

        public CatViewHolder(View itemView) {
            super(itemView);
            nameLabel = (TextView) itemView.findViewById(R.id.recycler_cell_cat_name_label);
            breedLabel = (TextView) itemView.findViewById(R.id.recycler_cell_cat_breed_label);
        }

    }
}
