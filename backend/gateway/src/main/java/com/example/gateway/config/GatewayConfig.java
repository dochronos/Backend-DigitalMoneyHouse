package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.gateway.security.AuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Value("${gateway.secret-token}")
    private String secretToken;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-server-logout", r -> r.path("/auth-server/api/logout")
                        .filters(f -> f
                                .filter(filter)
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8081"))
                .route("auth-server-login", r -> r.path("/auth-server/api/login")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8081"))
                .route("users-server-register", r -> r.path("/users-server/api/register")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8083"))
                .route("users-server", r -> r.path("/users-server/api/user/{id}")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8083"))
                .route("users-server", r -> r.path("/users-server/api/**")
                        .filters(f -> f
                                .filter(filter)
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8083"))
                .route("accounts-server", r -> r.path("/accounts-server/api/**")
                        .filters(f -> f
                                .filter(filter)
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/accounts-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8085"))
                .route("cards-server", r -> r.path("/cards-server/api/**")
                        .filters(f -> f
                                .filter(filter)
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/cards-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8087"))
                .route("activities-server", r -> r.path("/activities-server/api/**")
                        .filters(f -> f
                                .filter(filter)
                                .addRequestHeader("X-Secret-Token", secretToken)
                                .rewritePath("/activities-server/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8086"))
                .build();
    }

    @Bean
    public WebFilter corsWebFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            if (CorsUtils.isCorsRequest(exchange.getRequest())) {
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type");
                if ("OPTIONS".equals(exchange.getRequest().getMethod().name())) {
                    exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        };
    }
}
