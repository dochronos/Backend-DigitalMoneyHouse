package com.example.auth_server.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
        this(
            handledBy,
            exception,
            oriStatus,
            status,
            uri,
            localizedMessage,
            Collections.singletonList(message)
        );
    }

    public APIErrorEntity(
            String handledBy,
            String exception,
            HttpStatus oriStatus,
            HttpStatus status,
            String uri,
            String localizedMessage,
            List<String> messages
    ) {
        this.handledBy = handledBy;
        this.exception = exception;
        this.oriStatus = oriStatus;
        this.status = status;
        this.uri = uri;
        this.localizedMessage = localizedMessage;
        this.messages = messages;
    }
}
