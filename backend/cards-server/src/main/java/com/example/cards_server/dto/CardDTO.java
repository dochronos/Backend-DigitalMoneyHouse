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
    private String expiration;
    private String number;
    private String name;
    private Long userId;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.expiration = card.getExpiration();
        this.number = card.getNumber();
        this.name = card.getName();
        this.userId = card.getUserId();
    }
}
