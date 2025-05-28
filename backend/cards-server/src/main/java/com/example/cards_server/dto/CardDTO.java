package com.example.cards_server.dto;

import com.example.cards_server.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;
    private String number;
    private String expiration;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.expiration = card.getExpiration();
    }
}
