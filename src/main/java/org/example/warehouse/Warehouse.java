package org.example.warehouse;

import org.example.ProductRecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    // getter and setters

    public List<ProductRecord> getProductRecordList() {
        return productRecordList;
    }

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

        productRecordList.add(productRecord);

        return productRecord;
    }

    public List<ProductRecord> getProductById(UUID id) {
        return productRecordList.stream()
                .filter(product -> product.uuid().equals(id))
                .collect(Collectors.toList());
    }

    public List<ProductRecord> getProducts() {
        return productRecordList;
    }

    public void emptyListOfProducts(Warehouse warehouse) {
        if (warehouse.productRecordList == null) {
            warehouse.productRecordList = new ArrayList<>();
        }
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productRecordList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void updateProductPrice(UUID productId, BigDecimal newPrice) {
            for (ProductRecord product : productRecordList) {
                if (product.uuid().equals(productId)) {
                    product.setPrice(newPrice); // Update the price of the existing product
                    return;
                }
            }
            throw new IllegalArgumentException("Product with that UUID not found.");
        }


        public void productAfterChange() {
        UUID productId = changedProductList.get(1).uuid();
        BigDecimal newPrice = BigDecimal.valueOf(311, 2);
        updateProductPrice(productId, newPrice);

        List<ProductRecord> updatedProducts = getProductById(productId);
        if (updatedProducts.isEmpty()) {
            throw new RuntimeException("Product with UUID not found: " + productId);
        }

        ProductRecord updatedProduct = updatedProducts.get(0);
        if (!updatedProduct.price().equals(newPrice)) {
            throw new RuntimeException("Product price was not updated as expected.");
        } else {
            throw new RuntimeException("There are not enough changed products to test.");
        }




    public getChangedProducts() {
        }
    }
}

