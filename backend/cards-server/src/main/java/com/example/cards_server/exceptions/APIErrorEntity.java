package com.example.cards_server.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIErrorEntity {
    private String handledBy;
    private String exception;
    private HttpStatus oriStatus;
    private HttpStatus status;
    private String uri;
    private String localizedMessage;
    private List<String> messages;

    public APIErrorEntity(
            String handledBy,
            String exception,
            HttpStatus oriStatus,
            HttpStatus status,
            String uri,
            String localizedMessage,
            String message
    ) {
        this(handledBy, exception, oriStatus, status, uri, localizedMessage, Arrays.asList(message));
    }
}
