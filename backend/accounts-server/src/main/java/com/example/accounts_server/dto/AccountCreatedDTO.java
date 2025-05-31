package com.example.accounts_server.dto;

import com.example.accounts_server.entities.Account;

public class AccountCreatedDTO {

    private Long id;
    private String cvu;
    private String alias;

    public AccountCreatedDTO() {
    }

    public AccountCreatedDTO(Account account) {
        this.id = account.getId();
        this.cvu = account.getCvu();
        this.alias = account.getAlias();
    }

    public Long getId() {
        return id;
    }

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
