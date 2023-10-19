package org.example.warehouse;

import org.example.ProductRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Warehouse {
    //fields for warehouse, name, list of products to contain into
    private String warehouseName;
    private  List<ProductRecord> productRecordList ;
    //This I will need later
    private  List<ProductRecord> changedProductList ;
    // I am guessing
    private static Warehouse instance;

    // private constructor
    private Warehouse(){}
    private Warehouse (String name,List<ProductRecord> list){
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
    //get instance with and without name
    public static Warehouse getInstance(){
         if(instance==null)
             instance = new Warehouse();
        return new Warehouse();
    }

    public static Warehouse getInstance(String warehouseName) {
       /* return new Warehouse();*/
        if (instance == null) {
            instance = new Warehouse(warehouseName, new ArrayList<>());
            } else if (instance.getWarehouseName() == null) {
                instance.setWarehouseName(warehouseName);
            }
            return instance;

    }

    //same warehouse name then same warehouse
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

 class AfterAddingProducts {

        public List<ProductRecord> getProductById(UUID id) {
         return  productRecordList.stream()
                 .filter(product -> product.uuid().equals(id))
                 .collect(Collectors.toList());
     }


     public List<ProductRecord> invalidId(UUID Id){
         return
     }
     


 }




    public boolean isEmpty() {
        return false;
    }

   public Object addProduct(UUID uuid, String test, Category test1, Object o) {
        return o;
    }

    public Object getProductsBy(Category meat) {}

    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {
    }




    
}