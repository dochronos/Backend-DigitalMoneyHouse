package com.example.activities_server.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class APIErrorEntity {
    private String handledBy;
    private String exception;
    private HttpStatusCode status;
    private String uri;
    private String localizedMessage;
    private List<String> messages;

    public APIErrorEntity(
            final String handledBy,
            final String exception,
            final HttpStatusCode status,
            final String uri,
            final String localizedMessage,
            final List<String> messages
    ) {
        this.handledBy = handledBy;
        this.exception = exception;
        this.status = status;
        this.uri = uri;
        this.localizedMessage = localizedMessage;
        this.messages = messages != null ? messages : Collections.emptyList();
    }

    public APIErrorEntity(
            final String handledBy,
            final String exception,
            final HttpStatusCode status,
            final String uri,
            final String localizedMessage,
            final String message
    ) {
        this(handledBy, exception, status, uri, localizedMessage, List.of(message));
    }
}