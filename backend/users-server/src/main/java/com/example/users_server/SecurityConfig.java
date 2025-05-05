package com.example.users_server;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String SECRET_HEADER_NAME = "X-Secret-Token";
    private static final String SECRET_TOKEN = "from-gateway"; // ⚠️ Reemplazar por un valor más seguro en producción

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/exists/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new SecretTokenValidationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public static class SecretTokenValidationFilter extends OncePerRequestFilter {

        private static final Logger logger = LoggerFactory.getLogger(SecretTokenValidationFilter.class);

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String token = request.getHeader(SECRET_HEADER_NAME);
            logger.debug("Received Secret Token: {}", token);

            if (SECRET_TOKEN.equals(token)) {
                logger.info("Secret token is valid, proceeding with request.");

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        "user", null, Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            } else {
                logger.warn("Secret token is invalid, denying request.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
