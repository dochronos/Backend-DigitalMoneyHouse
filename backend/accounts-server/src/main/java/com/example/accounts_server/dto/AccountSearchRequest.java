package com.example.accounts_server.dto;

import lombok.Data;
@Data
public class AccountSearchRequest {
    private String cvu;
    private String alias;
}
