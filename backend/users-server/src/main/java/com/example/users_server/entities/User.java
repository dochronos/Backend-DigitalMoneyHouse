package com.example.users_server.entities;

import com.example.users_server.dto.UserDTO;
import com.example.users_server.dto.UserRegistrationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
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
    @Email
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

    public void update(UserDTO userUpdateDTO) {
        if (userUpdateDTO.getFirstName() != null) {
            this.firstName = userUpdateDTO.getFirstName();
        }
        if (userUpdateDTO.getLastName() != null) {
            this.lastName = userUpdateDTO.getLastName();
        }
        if (userUpdateDTO.getDni() != null) {
            this.dni = userUpdateDTO.getDni();
        }
        if (userUpdateDTO.getEmail() != null) {
            this.email = userUpdateDTO.getEmail();
        }
        if (userUpdateDTO.getPhone() != null) {
            this.phone = userUpdateDTO.getPhone();
        }
    }
}