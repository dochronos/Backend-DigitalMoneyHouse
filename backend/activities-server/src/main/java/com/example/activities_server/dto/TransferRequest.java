package com.example.activities_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @Positive
    private double amount;

    @NotBlank
    private String name;

    @NotBlank
    private String type;
}
