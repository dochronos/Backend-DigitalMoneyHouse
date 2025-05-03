package com.example.auth_server.dto;

import com.example.auth_server.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phone;

    public static UserRegisteredDTO fromEntity(User user) {
        return new UserRegisteredDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getDni(),
            user.getEmail(),
            user.getPhone()
        );
    }
}
