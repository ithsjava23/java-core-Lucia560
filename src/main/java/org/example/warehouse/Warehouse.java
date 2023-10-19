package org.example.warehouse;

import org.example.ProductRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Warehouse {

    private String warehouseName;
    private  List<ProductRecord> productRecordList ;

    private  List<ProductRecord> changedProductList ;

    private static Warehouse instance;

    // private constructor
    private Warehouse(){}
    private Warehouse (String name,List<ProductRecord> list){ // check without list
        this.warehouseName= name;
        this.productRecordList= new ArrayList<>(list);

    }
    // getter and setters
    public  List<ProductRecord> getChangedProductList() {
        return changedProductList;
    }
    public  List<ProductRecord> getProductRecordList() {
        return productRecordList;
    }

    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public static Warehouse getInstance(){
         if(instance==null)
             instance = new Warehouse();
        return new Warehouse();
    }

    public static Warehouse getInstance(String warehouseName) {
        if (instance == null) {
            instance = new Warehouse(warehouseName, new ArrayList<>());
            } else if (instance.getWarehouseName() == null) {
                instance.setWarehouseName(warehouseName);
            }
            return instance;

    }


    public static boolean sameWarehouse(Warehouse newWarehouse){
        return Warehouse.getInstance().equals(newWarehouse);
    }
    public List<ProductRecord> getProducts(Warehouse warehouse) {
        return warehouse.productRecordList;
    }

    public  ProductRecord addProduct(UUID uuid,String productName,Category category,BigDecimal price) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if(price == null) {
            price = BigDecimal.ZERO;
        }

        ProductRecord productRecord = new ProductRecord(uuid, productName, category, price);

        productRecordList.add(productRecord);

        return productRecord;
    }
    public  List<ProductRecord> getProductById(UUID id) {
        return  productRecordList.stream()
                .filter(product -> product.uuid().equals(id))
                .collect(Collectors.toList());
    }


    public List<ProductRecord> getProducts() {
        return List.of();
    }



    class whenNew{
      private Warehouse warehouse;
    public void  createWarehouse(){
         warehouse= Warehouse.getInstance();
    }
    public boolean isEmpty(Warehouse warehouse){
        return warehouse.isEmpty();
    }

    public void emptyListOfProducts (Warehouse warehouse) {
        if (warehouse.productRecordList == null) {
            warehouse.productRecordList = new ArrayList<>();
        }
    }

}

 class AfterAddingProduct {
     Warehouse warehouse = Warehouse.getInstance();
     UUID uuid ;
     String UUID_name;


    /*public  List<ProductRecord> getProductById(UUID id) {
         return  productRecordList.stream()
                 .filter(product -> product.uuid().equals(id))
                 .collect(Collectors.toList());
     }*/
     public List<ProductRecord> invalidId(){
         return warehouse.getProductById(UUID.fromString(UUID_name));
     }

}

class AfterAddingMultipleProducts{
        Warehouse warehouse = getInstance();
        List<ProductRecord> addedProducts = new ArrayList<>();




        
}


    public boolean isEmpty() {
        return false;
    }

    public Object getProductsBy(Category meat) {}

    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {
    }
    public boolean getChangedProducts() {
    }

    public boolean getProductsGroupedByCategories() {
    }



    
}