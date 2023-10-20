package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

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

    public static record ProductRecord(UUID uuid, String productName, Category category, BigDecimal price) {

        public ProductRecord(UUID uuid,String productName, Category category,BigDecimal price){
            this.productName = productName;
            this.category = category;
            this.price = price;
            this.uuid = uuid;
            }

        @Override
        public UUID uuid() {
            if(uuid ==null) {
                return UUID.randomUUID();}
            return uuid;
        }

        @Override
        public String productName() {
            return productName;
        }

        @Override
        public Category category() {
            return category;
        }

        @Override
        public BigDecimal price() {
            return price;
        }

        @Override
        public String toString() {
            return "ProductRecord{" +
                    "uuid=" + uuid +
                    ", productName='" + productName + '\'' +
                    ", category=" + category +
                    ", price=" + price +
                    '}';
        }


        public Object getCategory() {
            return category;
        }

        public BigDecimal setPrice(BigDecimal newPrice) {
            return newPrice;
        }
    }
}