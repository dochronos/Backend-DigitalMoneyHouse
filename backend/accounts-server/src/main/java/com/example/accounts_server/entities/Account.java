package com.example.accounts_server.entities;

import com.example.accounts_server.dto.UpdateDTO;
import com.example.accounts_server.dto.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false, unique = true)
    private String cvu;

    @Column(nullable = false, unique = true)
    private String alias;

    @Column(nullable = false)
    private String name;

    public Account() {
    }

    public Account(Long userId, Double balance, String cvu, String alias, String name) {
        this.userId = userId;
        this.balance = balance;
        this.cvu = cvu;
        this.alias = alias;
        this.name = name;
    }

    // Constructor adicional requerido por AccountService.java
    public Account(UserDTO userDTO, String cvu, String alias) {
        this.userId = userDTO.getId();
        this.balance = 0.0;
        this.cvu = cvu;
        this.alias = alias;
        this.name = userDTO.getFirstName() + " " + userDTO.getLastName();
    }

    // Método requerido por AccountService.java
    public void update(UpdateDTO updateDTO) {
        if (updateDTO.getAlias() != null) {
            this.alias = updateDTO.getAlias();
        }
        if (updateDTO.getCvu() != null) {
            this.cvu = updateDTO.getCvu();
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setName(String name) {
        this.name = name;
    }
}
