package org.example.warehouse;

import org.example.ProductRecord;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    //fields
    private String warehouseName;
    private List<ProductRecord> productRecordList;

    private List<ProductRecord> changedProductList = new ArrayList<>();

    //constructors
    private static Warehouse instance;

    // private constructor
    private Warehouse() {
    }

    private Warehouse(String name, List<ProductRecord> list) {
        this.warehouseName = name;
        this.productRecordList = new ArrayList<>(list);

    }

    // getter and setter
    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // public constructor
    public static Warehouse getInstance() {
        if (instance == null)
            instance = new Warehouse();
        return instance;
    }

    public static Warehouse getInstance(String warehouseName) {
        if (instance == null) {
            instance = new Warehouse(warehouseName, new ArrayList<>());
        } else if (instance.getWarehouseName() == null) {
            instance.setWarehouseName(warehouseName);
        }
        return instance;

    }

    public static boolean sameWarehouse(Warehouse newWarehouse) {
        return Warehouse.getInstance().equals(newWarehouse);
    }

    public List<ProductRecord> getProducts(Warehouse warehouse) {
        return warehouse.productRecordList;
    }

    public boolean isEmpty() {
        return productRecordList.isEmpty();
    }

    // add product
    public ProductRecord addProduct(UUID uuid, String productName, Category category, BigDecimal price) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }

        ProductRecord productRecord = new ProductRecord(uuid, productName, category, price);
        if (productRecordList.stream().anyMatch(p -> p.uuid().equals(productRecord.uuid()))) {
            throw new IllegalArgumentException("Product with this ID  already exists");
        }

        productRecordList.add(productRecord);

        return productRecord;
    }

    public List<ProductRecord> getProductById(UUID id) {
        return productRecordList.stream()
                .filter(product -> product.uuid().equals(id))
                .collect(Collectors.toList());
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productRecordList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    // correct
    public List<ProductRecord> getProducts() {
        return productRecordList;
    }

    public void emptyListOfProducts(Warehouse warehouse) {
        if (warehouse.productRecordList == null) {
            warehouse.productRecordList = new ArrayList<>();
        }
    }


   public Optional<ProductRecord> updateProductPrice(UUID productId, BigDecimal newPrice) {
       for (ProductRecord product : productRecordList) {
           if (product.uuid().equals(productId)) {
               ProductRecord updatedProduct = new ProductRecord(productId, product.productName(), product.category(), newPrice);
               changedProductList.add(updatedProduct);
               productRecordList.remove(product);
               productRecordList.add(updatedProduct);
               return Optional.of(updatedProduct);
           }
       }
       return Optional.empty(); // Product with that UUID not found.
   }

    public void saveProductChanges() {
        UUID productId = productRecordList.get(0).uuid();
        BigDecimal newPrice = BigDecimal.valueOf(311, 2);
        Optional<ProductRecord> updatedProduct = updateProductPrice(productId, newPrice);

        if (updatedProduct.isPresent()) {
            ProductRecord product = updatedProduct.get();
            if (!product.price().equals(newPrice)) {
                throw new AssertionError("Product price was not updated as expected.");
            }
        } else {
            throw new AssertionError("Expected product with ID " + productId + " to be updated.");
        }
    }

    public List<ProductRecord> getChangedProducts() {
        return changedProductList;
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return productRecordList.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

}