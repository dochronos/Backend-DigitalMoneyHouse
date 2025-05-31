package com.example.accounts_server.dto;

public class UpdateDTO {

    private String cvu;
    private String alias;

    public UpdateDTO() {
    }

    public UpdateDTO(String cvu, String alias) {
        this.cvu = cvu;
        this.alias = alias;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
