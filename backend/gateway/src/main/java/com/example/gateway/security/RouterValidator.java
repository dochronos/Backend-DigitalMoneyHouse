package com.example.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    private static final List<String> openApiEndpoints = List.of(
            "/auth-server/api/login",
            "/users-server/api/register",
            "/mobile/swagger-ui/index.html"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().equalsIgnoreCase(uri));
}
