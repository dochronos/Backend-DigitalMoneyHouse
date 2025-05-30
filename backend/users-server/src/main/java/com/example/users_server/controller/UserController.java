package com.example.users_server.controller;

import com.example.users_server.dto.UserDTO;
import com.example.users_server.dto.UserRegisteredDTO;
import com.example.users_server.dto.UserRegistrationDTO;
import com.example.users_server.exceptions.BadRequestException;
import com.example.users_server.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisteredDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        Optional.ofNullable(userRegistrationDTO)
                .orElseThrow(() -> new BadRequestException("Registration data must not be null"));

        userService.findByEmail(userRegistrationDTO.getEmail()).ifPresent(user -> {
            throw new BadRequestException("Email already registered");
        });

        UserRegisteredDTO registeredUser = userService.registerUser(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/exists/{id}")
    public ResponseEntity<Boolean> userExists(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.userExists(id));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,
                                              @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
