package com.example.users_server.services;

import com.example.users_server.dto.*;
import com.example.users_server.entities.User;
import com.example.users_server.exceptions.ResourceNotFoundException;
import com.example.users_server.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountClient accountClient;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       AccountClient accountClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountClient = accountClient;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserRegisteredDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        User user = new User(userRegistrationDTO, encodedPassword);
        User savedUser = userRepository.save(user);

        AccountCreatedDTO createdAccount = accountClient.createAccount(savedUser.getId());

        return new UserRegisteredDTO(savedUser, createdAccount);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + id)
        );
        return new UserDTO(user);
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + id)
        );
        user.update(userDTO);
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }
}
