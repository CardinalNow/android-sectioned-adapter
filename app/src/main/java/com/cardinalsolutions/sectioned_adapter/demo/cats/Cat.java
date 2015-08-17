package com.cardinalsolutions.sectioned_adapter.demo.cats;

import com.cardinalsolutions.sectioned_adapter.Categorizable;

public class Cat implements Categorizable {
    String name;
    String breed;

    public Cat(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    @Override
    public String getCategory() {
        return breed;
    }
}
