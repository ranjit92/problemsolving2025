package org.example.lld.inventory;

public class Product {

    String productId;
    String productName;
    double price;

    Product(String productId, String productName, double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
}
