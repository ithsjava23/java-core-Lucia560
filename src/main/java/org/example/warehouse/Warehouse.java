package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class Warehouse {
    //fields
    private String warehouseName;
    private List<ProductRecord> productRecordList = new ArrayList<>();

    private List<ProductRecord> changedProductList = new ArrayList<>();

    //constructors
    private static Warehouse instance;

    // private constructor
    private Warehouse() {
    }

    private Warehouse(String name) {
        this.warehouseName = name;

    }

    // getter and setter
    public String getName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // public constructor
    public static Warehouse getInstance() {
   return new Warehouse();
    }

    public static Warehouse getInstance(String warehouseName) {
        return new Warehouse(warehouseName);

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
        if(getProductById(uuid).isPresent()){
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord productRecord = new ProductRecord(uuid, productName, category, price);
        productRecordList.add(productRecord);
        return productRecord;
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return productRecordList.stream()
                .filter(product -> product.uuid().equals(id))
                .findFirst();
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productRecordList.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(productRecordList);
    }


    public void emptyListOfProducts(Warehouse warehouse) {
        if (warehouse.productRecordList == null) {
            warehouse.productRecordList = new ArrayList<>();
        }
    }


    public Optional<ProductRecord> updateProductPrice(UUID productId, BigDecimal newPrice) {
        ProductRecord existingProduct = productRecordList.stream()
                .filter(p -> p.uuid().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with that id doesn't exist."));

        changedProductList.add(existingProduct);

        ProductRecord updatedProduct = new ProductRecord(productId, existingProduct.name(), existingProduct.category(), newPrice);
        productRecordList.remove(existingProduct);
        productRecordList.add(updatedProduct);

        return Optional.of(updatedProduct);
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
        return List.copyOf(changedProductList);
    }

   public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return productRecordList.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

}