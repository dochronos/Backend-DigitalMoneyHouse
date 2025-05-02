package com.example.activities_server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    @NotNull
    private Double amount;

    @Column(name = "date", nullable = false)
    @NotBlank
    private String date;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Long userId;

    @Column(name = "origin", nullable = false)
    @NotBlank
    private String origin;

    @Column(name = "destination", nullable = false)
    @NotBlank
    private String destination;

    @Column(name = "detail")
    private String detail;

    @Column(name = "type", nullable = false)
    @NotBlank
    private String type;

    // Campo opcional que puede estar asociado a una tarjeta
    @Column(name = "card_id")
    private Long cardId;
}