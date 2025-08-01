package org.example.lld.inventory;

import java.util.ArrayList;
import java.util.List;

public class SolutionMain {

    SellerManager sellerManager = new SellerManager();
    ProductManager productManager = new ProductManager();


    public SolutionMain() {
    }

    public void createSeller(String sellerId, List<String> serviceablePinCodes, List<String> supportedPayments) {
        sellerManager.createSeller(sellerId, serviceablePinCodes, supportedPayments);
    }

    public void createProduct(String productId, String productName, double price) {
        productManager.createProduct(productId, productName, price);
    }

    public String addInventory(String productId, String sellerId, int itemCount) {
        if (productId == null) {
            return "Product doesn't exist";
        }
        if (sellerId == null) {
            return "Seller doesn't exits";
        }
        return productManager.addInventory(productId, sellerId, itemCount);
    }

    public String createOrder(String orderId, String productId,
                              String sellerId, int itemCount,
                              String destinationPin, String paymentsMode) {

        Product product = productManager.getProduct(productId);
        if (product == null) {
            return "Product not available";
        }

        Seller seller = sellerManager.getSeller(sellerId);
        if (seller == null) {
            return "Seller not available";
        }

        if (!seller.isPaymentModeAvailable(paymentsMode)) {
            return "payment mode not supported";
        }
        if (!seller.isPinServiceable(destinationPin)) {
            return "pincode unserviceable";
        }

        boolean reduced = productManager.reduceInventory(productId, sellerId, itemCount);

        return reduced ? "Order placed" : "insufficient product inventory";
    }

    public int getInventory(String productId, String sellerId) {
        return productManager.getInventory(productId, sellerId);
    }

    public List<String> getSeller(String productId, int itemCount,
                                  String destinationPin, String paymentMode){

        return productManager.getSellers(productId, itemCount, destinationPin, paymentMode, sellerManager);
    }

    public static void main(String[] args) {
        SolutionMain sm = new SolutionMain();

        sm.createSeller("seller1", List.of("67234", "56432", "65434"), List.of("UPI", "COD", "CARD"));
        sm.createSeller("seller2", List.of("67236", "56436", "65439"), List.of("COD", "CARD"));

        sm.createProduct("product1", "product1", 45);
        sm.createProduct("produt2", "product2", 12);

        sm.addInventory("product1", "seller1", 50);
        sm.addInventory("product2", "seller1", 60);
        sm.addInventory("product1", "seller2", 10);

        String order1 = sm.createOrder("ord1", "product1", "seller1", 5, "65434", "COD");

        System.out.println(sm.getInventory("product1", "seller1"));

        String order2 = sm.createOrder("ord2", "product1", "seller2", 11, "65439", "COD");
        System.out.println(order2);

    }
}
