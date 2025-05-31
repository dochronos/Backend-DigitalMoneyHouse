package com.example.activities_server.dto;

public class LoadMoneyRequest {
    private Long userId;
    private Double amount;
    private String cardNumber;

    public LoadMoneyRequest() {
    }

    public LoadMoneyRequest(Long userId, Double amount, String cardNumber) {
        this.userId = userId;
        this.amount = amount;
        this.cardNumber = cardNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
