package org.example.lld.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Seller {

    String sellerId;
    Set<String> serviceablePinCode = new HashSet<>();
    Set<String> sellerPaymentsModes = new HashSet<>();

    Seller(String sellerId, List<String> serviceablePinCode, List<String> sellerPaymentsModes){
        this.sellerId = sellerId;
        this.serviceablePinCode.addAll(serviceablePinCode);
        this.sellerPaymentsModes.addAll(sellerPaymentsModes);
    }

    public boolean isPinServiceable(String pinCode){
        return pinCode != null && this.serviceablePinCode.contains(pinCode);
    }

    public boolean isPaymentModeAvailable(String paymentMode){
        return paymentMode != null && this.sellerPaymentsModes.contains(paymentMode);
    }
}
