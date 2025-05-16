package com.example.cards_server.dto;

import com.example.cards_server.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCreateDTO {

    @NotBlank
    private String expiration;

    @NotBlank
    private String number;

    @NotBlank
    private String name;

    @NotBlank
    private String cvc;

    @NotNull
    private Long userId;

    public CardCreateDTO(Card card) {
        this.expiration = card.getExpiration();
        this.number = card.getNumber();
        this.name = card.getName();
        this.cvc = card.getCvc();
        this.userId = card.getUserId();
    }
}
