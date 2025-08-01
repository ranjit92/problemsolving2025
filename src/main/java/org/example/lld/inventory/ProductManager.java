package org.example.lld.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductManager {

    ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();

    //map of product key contains inside map that stores
    // seller id and number of item available of that product
    ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> productInventory = new ConcurrentHashMap<>();

    public void createProduct(String productId, String productName, double price) {
        products.put(productId, new Product(productId, productName, price));
    }

    public Product getProduct(String productId) {
        if (products.containsKey(productId)) {
            return products.get(productId);
        }
        return null;
    }

    public String addInventory(String productId, String sellerId, int itemCount) {
        productInventory.putIfAbsent(productId, new ConcurrentHashMap<>());

        ConcurrentHashMap<String, AtomicInteger> sellerInventory = productInventory.get(productId);
        sellerInventory.putIfAbsent(sellerId, new AtomicInteger(0));

        sellerInventory.get(sellerId).addAndGet(itemCount);
        return "inventory added";
    }

    public boolean reduceInventory(String productId, String sellerId, int delta) {
        AtomicInteger itemCount = getInternalInventory(productId, sellerId);
        if (itemCount != null) {
            while (true) {
                int currentVal = itemCount.get();
                if (itemCount.get() < delta) {
                    break;
                }
                if (itemCount.compareAndSet(currentVal, currentVal - delta)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getInventory(String productId, String sellerId) {
        AtomicInteger inventory = getInternalInventory(productId, sellerId);
        return inventory == null ? 0 : inventory.get();
    }

    // return all sellers who have product with enough inventory to satisfy the order
    public List<String> getSellers(String productId, int itemCount,
                                   String destinationPin, String paymentMode,
                                   SellerManager sellerManager) {

        List<String> sellerIds = new ArrayList<>();
        ConcurrentHashMap<String, AtomicInteger> sellerInventory = productInventory.get(productId);

        for (String sellerId : sellerInventory.keySet()) {
            Seller seller = sellerManager.getSeller(sellerId);

            if (seller != null && seller.isPinServiceable(destinationPin)
                    && seller.isPaymentModeAvailable(paymentMode)
                    && sellerInventory.get(sellerId).get() >= itemCount) {
                sellerIds.add(sellerId);
            }
        }
        return sellerIds;
    }

    private AtomicInteger getInternalInventory(String productId, String sellerId) {
        ConcurrentHashMap<String, AtomicInteger> sellerInventory = productInventory.get(productId);

        if (sellerInventory != null) {
            return sellerInventory.get(sellerId);
        }
        return null;
    }
}
