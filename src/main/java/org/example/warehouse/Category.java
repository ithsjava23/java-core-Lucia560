package org.example.warehouse;

import java.util.Objects;

public class Category {

    private  String name;

    private Category(String name) {
        if (name == null){
            throw new IllegalArgumentException("Category name can't be null");}
        if (!name.isEmpty() && Character.isLowerCase(name.charAt(0))) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        this.name= name;

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

    public boolean sameName(Category newCategory) {
            return this.name.equals(newCategory.getName());
        }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}