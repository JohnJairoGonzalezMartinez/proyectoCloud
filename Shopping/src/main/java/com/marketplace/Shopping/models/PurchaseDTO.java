package com.marketplace.Shopping.models;

public class PurchaseDTO {

    private String serviceId;
    private int quantity;

    public PurchaseDTO(String serviceId, int quantity) {
        this.serviceId = serviceId;
        this.quantity = quantity;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
