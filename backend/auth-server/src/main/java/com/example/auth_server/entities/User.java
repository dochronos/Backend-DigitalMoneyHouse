package com.example.auth_server.entities;

import com.example.auth_server.dto.UserRegistrationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "dni", nullable = false)
    @NotBlank
    private String dni;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "phone", nullable = false)
    @NotBlank
    private String phone;

    public User(UserRegistrationDTO userRegistrationDTO, String encodedPassword) {
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.dni = userRegistrationDTO.getDni();
        this.email = userRegistrationDTO.getEmail();
        this.password = encodedPassword;
        this.phone = userRegistrationDTO.getPhone();
    }
}