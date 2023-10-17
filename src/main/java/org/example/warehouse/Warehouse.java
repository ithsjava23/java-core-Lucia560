package org.example.warehouse;

import org.example.ProductRecord;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {
    private String warehouseName;
    String warehouse;
    private static  List<Warehouse> listOfWarehouse;
    public static List<ProductRecord> productList = new ArrayList<>();
    private Warehouse (){
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public static Warehouse getInstance(){
        return new Warehouse();
    }

    public static Warehouse getInstance(String warehouseName) {
        return new Warehouse();
    }

    public static boolean sameWarehouse(Warehouse newWarehouse){
        return listOfWarehouse.stream().anyMatch(warehouse -> warehouse.equals(newWarehouse));
    }

class NewWarehouse{
    List<Warehouse> listOfWarehouse = new ArrayList<>();
    public void newWarehouse (){
            Warehouse warehouse1;
            warehouse1 = getInstance(warehouseName);
    }
    public boolean isEmpty(){
       return warehouse.isEmpty();
    }

    //return empty list of products



}

 class AfterAddingProducts{
        public ProductRecord addedProduct;
         public String name;
         public  UUID uuid;

 public void addingProduct(ProductRecord productRecord){

 }




 }

    public boolean isEmpty() {
        return false;
    }

    public Object addProduct(UUID uuid, String test, Category test1, Object o) {
        return o;
    }

    public boolean getProductsBy(Category meat) {
        return false;
    }

    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {
    }


    public boolean getProducts() {
    }

  class afterAddingMultipleProducts{

  }
}