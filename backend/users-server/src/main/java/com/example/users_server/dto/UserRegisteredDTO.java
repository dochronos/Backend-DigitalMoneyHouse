package com.example.users_server.dto;

import com.example.users_server.entities.User;

public class UserRegisteredDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phone;
    private AccountCreatedDTO account;

    public UserRegisteredDTO() {
    }

    public UserRegisteredDTO(User user, AccountCreatedDTO account) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountCreatedDTO getAccount() {
        return account;
    }

    public void setAccount(AccountCreatedDTO account) {
        this.account = account;
    }
}
