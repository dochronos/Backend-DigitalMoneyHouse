package com.example.cards_server.entities;

import com.example.cards_server.dto.CardCreateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration", nullable = false)
    private String expiration;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cvc", nullable = false)
    private String cvc;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Card() {
    }

    public Card(CardCreateDTO dto) {
        this.expiration = dto.getExpiration();
        this.number = dto.getNumber();
        this.name = dto.getName();
        this.cvc = dto.getCvc();
        this.userId = dto.getUserId();
    }

    // Getters y setters explícitos
    public Long getId() {
        return id;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
