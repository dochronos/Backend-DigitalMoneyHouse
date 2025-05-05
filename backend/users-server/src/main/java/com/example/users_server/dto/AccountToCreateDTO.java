package com.example.users_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountToCreateDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
