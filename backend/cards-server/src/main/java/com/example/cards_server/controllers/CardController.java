package com.example.cards_server.controllers;

import com.example.cards_server.dto.CardCreateDTO;
import com.example.cards_server.dto.CardDTO;
import com.example.cards_server.exceptions.CardAlreadyExistsException;
import com.example.cards_server.exceptions.ResourceNotFoundException;
import com.example.cards_server.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts/{userId}/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getCardsByUserId(@PathVariable Long userId) {
        try {
            List<CardDTO> cards = cardService.getCardsByUserId(userId);
            return ResponseEntity.ok(cards);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long userId, @PathVariable Long cardId) {
        try {
            CardDTO card = cardService.getCardById(userId, cardId);
            return ResponseEntity.ok(card);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/by-number/{cardNumber}")
    public ResponseEntity<CardDTO> getCardByLastFourNumbers(@PathVariable Long userId, @PathVariable String cardNumber) {
        try {
            CardDTO card = cardService.getCardByNumber(userId, cardNumber);
            return ResponseEntity.ok(card);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCardForUser(@PathVariable Long userId, @RequestBody CardCreateDTO cardCreateDTO) {
        try {
            CardDTO createdCard = cardService.createCardForUser(userId, cardCreateDTO);
            return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
        } catch (CardAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long userId, @PathVariable Long cardId) {
        try {
            cardService.deleteCardForUser(userId, cardId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}