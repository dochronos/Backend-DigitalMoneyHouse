package com.example.activities_server.dto;

import com.example.activities_server.entities.Activity;

public class ActivityDTO {

    private Long userId;
    private String origin;
    private String destination;
    private Double amount;
    private String type;
    private Long cardId;
    private String date;
    private String detail;

    public ActivityDTO() {
    }

    public ActivityDTO(Long userId, String origin, String destination, Double amount, String type, Long cardId, String date, String detail) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.type = type;
        this.cardId = cardId;
        this.date = date;
        this.detail = detail;
    }

    public ActivityDTO(Activity activity) {
        this.userId = activity.getUserId();
        this.origin = activity.getOrigin();
        this.destination = activity.getDestination();
        this.amount = activity.getAmount();
        this.type = activity.getType();
        this.cardId = activity.getCardId();
        this.date = activity.getDate();
        this.detail = activity.getDetail();
    }

    public Long getUserId() {
        return userId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getDate() {
        return date;
    }

    public String getDetail() {
        return detail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
