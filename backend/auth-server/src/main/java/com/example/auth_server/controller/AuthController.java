package com.example.auth_server.controller;

import com.example.auth_server.dto.TokenResponseDTO;
import com.example.auth_server.dto.UserLoginDTO;
import com.example.auth_server.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody UserLoginDTO loginRequestDTO) {
        TokenResponseDTO tokenResponseDTO = authService.loginUser(loginRequestDTO);
        return new ResponseEntity<>(tokenResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String token) {
        authService.logoutUser(token);
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}
