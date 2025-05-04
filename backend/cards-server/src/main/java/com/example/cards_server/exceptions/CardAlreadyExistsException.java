package com.example.cards_server.exceptions;

public class CardAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CardAlreadyExistsException() {
        super();
    }

    public CardAlreadyExistsException(String message) {
        super(message);
    }
}