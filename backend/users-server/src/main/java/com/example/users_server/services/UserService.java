package com.example.users_server.services;

import com.example.users_server.clients.AccountServiceClient;
import com.example.users_server.dto.*;
import com.example.users_server.entities.User;
import com.example.users_server.exceptions.BadRequestException;
import com.example.users_server.exceptions.ResourceNotFoundException;
import com.example.users_server.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountServiceClient accountServiceClient;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AccountServiceClient accountServiceClient,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountServiceClient = accountServiceClient;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisteredDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Email already registered");
        }

        User user = new User(userRegistrationDTO, passwordEncoder.encode(userRegistrationDTO.getPassword()));
        User savedUser = userRepository.save(user);

        AccountToCreateDTO accountToCreateDTO = new AccountToCreateDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName());
        AccountCreatedDTO accountCreatedDTO = accountServiceClient.createAccount(accountToCreateDTO);

        return new UserRegisteredDTO(savedUser, accountCreatedDTO);
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return new UserDTO(user);
    }

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        user.update(userDTO);
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
