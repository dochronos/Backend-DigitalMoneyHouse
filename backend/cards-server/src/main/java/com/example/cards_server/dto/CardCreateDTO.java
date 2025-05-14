package com.example.cards_server.dto;

import com.example.cards_server.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCreateDTO {
    private String expiration;
    private String number;
    private String name;
    private String cvc;
    private Long userId;

    public CardCreateDTO(Card card) {
        this.expiration = card.getExpiration();
        this.number = card.getNumber();
        this.name = card.getName();
        this.cvc = card.getCvc();
        this.userId = card.getUserId();
    }
}
