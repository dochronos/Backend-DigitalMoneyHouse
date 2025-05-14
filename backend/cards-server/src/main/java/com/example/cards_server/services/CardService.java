package com.example.cards_server.services;

import com.example.cards_server.clients.UserClient;
import com.example.cards_server.dto.CardCreateDTO;
import com.example.cards_server.dto.CardDTO;
import com.example.cards_server.entities.Card;
import com.example.cards_server.exceptions.CardAlreadyExistsException;
import com.example.cards_server.exceptions.ResourceNotFoundException;
import com.example.cards_server.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final UserClient userClient;

    public CardService(CardRepository cardRepository, UserClient userClient) {
        this.cardRepository = cardRepository;
        this.userClient = userClient;
    }

    public List<CardDTO> getCardsByUserId(Long userId) {
        validateUserExists(userId);

        return cardRepository.findAllByUserId(userId).stream()
                .map(CardDTO::new)
                .collect(Collectors.toList());
    }

    public CardDTO getCardById(Long userId, Long cardId) {
        validateUserExists(userId);

        Card card = cardRepository.findByIdAndUserId(cardId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Card not found for cardId " + cardId + " and userId " + userId
                ));

        return new CardDTO(card);
    }

    public CardDTO createCardForUser(Long userId, CardCreateDTO cardCreateDTO) {
        cardRepository.findByNumber(cardCreateDTO.getNumber()).ifPresent(card -> {
            throw new CardAlreadyExistsException(
                    "Card with number " + cardCreateDTO.getNumber() + " already exists."
            );
        });

        Card card = new Card(cardCreateDTO);
        card.setUserId(userId);

        Card savedCard = cardRepository.save(card);

        return new CardDTO(savedCard);
    }

    public void deleteCardForUser(Long userId, Long cardId) {
        if (!cardRepository.existsByIdAndUserId(cardId, userId)) {
            throw new ResourceNotFoundException(
                    "Card not found for cardId " + cardId + " and userId " + userId
            );
        }

        cardRepository.deleteById(cardId);
    }

    public CardDTO getCardByNumber(Long userId, String cardNumberSuffix) {
        validateUserExists(userId);

        Card card = cardRepository.findByNumberEndingWith(cardNumberSuffix)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Card not found for number ending with " + cardNumberSuffix + " and userId " + userId
                ));

        return new CardDTO(card);
    }

    private void validateUserExists(Long userId) {
        if (!userClient.userExists(userId)) {
            throw new ResourceNotFoundException("User not found for userId " + userId);
        }
    }
}
