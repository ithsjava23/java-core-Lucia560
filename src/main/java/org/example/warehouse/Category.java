package org.example.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Category {
    private String name;
    List<Category> categoryList = new ArrayList<>();
    private Category(String name) {
        this.name= name;

    }public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static Category of(String s) {
        return new Category(s);
    }
    public boolean sameName(Category newCategory) {
        return categoryList.stream().anyMatch( category-> category.getName().equals(newCategory.getName()));
    }
    public void checkFirstLetter() {
        if (!name.isEmpty() && Character.isLowerCase(name.charAt(0))) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
    }
    public void nullName(String s) {
        if (s == null)
            throw new IllegalArgumentException("Category name can't be null");
    }
}