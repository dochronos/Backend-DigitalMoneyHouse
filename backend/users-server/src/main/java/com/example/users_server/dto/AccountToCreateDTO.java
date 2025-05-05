package com.example.users_server.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountToCreateDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
