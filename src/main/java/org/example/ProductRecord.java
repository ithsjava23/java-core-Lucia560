package org.example;

import org.example.warehouse.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecord(UUID uuid,String productName, Category category,BigDecimal price) {

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



}






