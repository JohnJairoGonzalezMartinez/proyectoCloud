package com.marketplace.Marketplace.models;

public class CartItem {

    private String serviceId;
    private int quantity;

    public CartItem(){}

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
