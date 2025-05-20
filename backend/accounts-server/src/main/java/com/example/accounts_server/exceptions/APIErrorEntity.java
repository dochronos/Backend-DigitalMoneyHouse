package com.example.accounts_server.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class APIErrorEntity {
    private String handledBy;
    private String exception;
    private HttpStatus status;
    private String uri;
    private String localizedMessage;
    private List<String> messages;

    public APIErrorEntity() {}

    public APIErrorEntity(String handledBy, String exception, HttpStatus status, String uri, String localizedMessage, List<String> messages) {
        this.handledBy = handledBy;
        this.exception = exception;
        this.status = status;
        this.uri = uri;
        this.localizedMessage = localizedMessage;
        this.messages = messages;
    }

    public APIErrorEntity(String handledBy, String exception, HttpStatus status, String uri, String localizedMessage, String message) {
        this(handledBy, exception, status, uri, localizedMessage, Arrays.asList(message));
    }

    // Getters necesarios
    public String getHandledBy() {
        return handledBy;
    }

    public String getException() {
        return exception;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getUri() {
        return uri;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public List<String> getMessages() {
        return messages;
    }
}
