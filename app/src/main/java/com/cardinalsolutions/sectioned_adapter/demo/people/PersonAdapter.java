package com.cardinalsolutions.sectioned_adapter.demo.people;

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
 * A SectionedAdapter subclass that adapts Person objects to a list sectioned by the first letter
 * of each Person's last name.
 */
public class PersonAdapter extends SectionedAdapter<Person> {

    public PersonAdapter() {
        List<Person> yankees = new ArrayList<>();
        yankees.add(new Person("Earle", "Combs"));
        yankees.add(new Person("Mark", "Koenig"));
        yankees.add(new Person("Babe", "Ruth"));
        yankees.add(new Person("Lou", "Gehrig"));
        yankees.add(new Person("Bob", "Meusel"));
        yankees.add(new Person("Tony", "Lazzeri"));
        yankees.add(new Person("Joe", "Dugan"));
        yankees.add(new Person("Pat", "Collins"));
        yankees.add(new Person("Benny", "Bengough"));
        yankees.add(new Person("Johnny", "Grabowski"));
        yankees.add(new Person("Mike", "Gazella"));
        yankees.add(new Person("Ray", "Morehart"));
        yankees.add(new Person("Julie", "Wera"));
        yankees.add(new Person("Cedric", "Durst"));
        yankees.add(new Person("Ben", "Paschal"));
        yankees.add(new Person("Waite", "Hoyt"));
        yankees.add(new Person("Herb", "Pennock"));
        yankees.add(new Person("George", "Pipgras"));
        yankees.add(new Person("Dutch", "Ruether"));
        yankees.add(new Person("Urban", "Shocker"));
        yankees.add(new Person("Wilcy", "Moore"));
        this.setItemList(yankees);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, Person item, @ViewType int viewType) {
        ((PersonViewHolder) holder).nameView.setText(item.firstName + " " + item.lastName);
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, @ViewType int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell_person, parent, false);
        return new PersonViewHolder(view);
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.recycler_cell_person_name_label);
        }
    }
}
