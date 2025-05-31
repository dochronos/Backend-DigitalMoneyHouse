package com.example.activities_server.entities;

import com.example.activities_server.dto.ActivityDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String origin;
    private String destination;
    private Double amount;
    private String type;
    private Long cardId;
    private String date;
    private String detail;

    public Activity() {
    }

    public Activity(Long userId, String origin, String destination, Double amount, String type, Long cardId, String date, String detail) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.type = type;
        this.cardId = cardId;
        this.date = date;
        this.detail = detail;
    }

    public Activity(ActivityDTO dto) {
        this.userId = dto.getUserId();
        this.origin = dto.getOrigin();
        this.destination = dto.getDestination();
        this.amount = dto.getAmount();
        this.type = dto.getType();
        this.cardId = dto.getCardId();
        this.date = dto.getDate();
        this.detail = dto.getDetail();
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
