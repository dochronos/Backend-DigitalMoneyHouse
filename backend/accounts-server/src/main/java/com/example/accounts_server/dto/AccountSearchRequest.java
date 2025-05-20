package com.example.accounts_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountSearchRequest {
    private String cvu;
    private String alias;

    public String getCvu() {
        return cvu;
    }

    public String getAlias() {
        return alias;
    }
}
