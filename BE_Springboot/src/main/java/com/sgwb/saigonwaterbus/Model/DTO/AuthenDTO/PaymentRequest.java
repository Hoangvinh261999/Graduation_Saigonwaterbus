package com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO;

public class PaymentRequest {
    private String orderId;
    private int amount;
    private String returnUrl;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
    // Getters and Setters
}
