package org.example.warehouse;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    List<Category> categoryList = new ArrayList<>();
    private Category(String name) {
        this.name = name;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public static Category of(String name) {
        return new Category(name);
    }
    public boolean getName() {
    }


}
public record ProductRecord()
