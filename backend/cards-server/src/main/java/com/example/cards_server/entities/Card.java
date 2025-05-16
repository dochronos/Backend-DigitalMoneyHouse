package com.example.cards_server.entities;

import com.example.cards_server.dto.CardCreateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expiration", nullable = false)
    @NotNull
    private String expiration;

    @Column(name = "number", nullable = false, unique = true)
    @NotBlank
    private String number;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "cvc", nullable = false)
    @NotBlank
    private String cvc;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private Long userId;

    public Card(CardCreateDTO dto) {
        this.expiration = dto.getExpiration();
        this.number = dto.getNumber();
        this.name = dto.getName();
        this.cvc = dto.getCvc();
        this.userId = dto.getUserId();
    }
}
