package com.example.users_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedDTO {
    private Long userId;
    private BigDecimal balance;
    private String cvu;
    private String alias;
    private String name;
}
