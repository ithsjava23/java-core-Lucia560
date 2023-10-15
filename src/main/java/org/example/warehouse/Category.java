package org.example.warehouse;

import java.util.Objects;

public class Category {
    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        return new Category(name);
    }
    public boolean sameName(String newName) {
        return Objects.equals(newName, getName());
    }
    public void checkFirstLetter(String n){


    }
    public void nullName(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
    }


    public record ProductRecord(String s){
    }
}