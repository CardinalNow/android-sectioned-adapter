package com.cardinalsolutions.sectioned_adapter.demo.people;

import com.cardinalsolutions.sectioned_adapter.Categorizable;

public class Person implements Categorizable {
    String firstName;
    String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getCategory() {
        return this.lastName.substring(0, 1);
    }
}