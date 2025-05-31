package com.example.activities_server.dto;

public class AccountSearchRequest {

    private String alias;
    private String cvu;

    public AccountSearchRequest() {
    }

    public AccountSearchRequest(String alias, String cvu) {
        this.alias = alias;
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }
}
