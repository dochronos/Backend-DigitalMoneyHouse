package com.example.accounts_server.dto;;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
