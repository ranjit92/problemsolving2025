package org.example.lld.inventory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SellerManager {

    ConcurrentHashMap<String, Seller> sellersMap = new ConcurrentHashMap<>();

    public void createSeller(String sellerId, List<String> serviceablePinCodes, List<String> supportedPayments){
        Seller seller = new Seller(sellerId, serviceablePinCodes, supportedPayments);
        sellersMap.put(sellerId, seller);
    }

    public Seller getSeller(String sellerId){
        if(sellersMap.containsKey(sellerId)){
            return sellersMap.get(sellerId);
        }
        return null;
    }
}
