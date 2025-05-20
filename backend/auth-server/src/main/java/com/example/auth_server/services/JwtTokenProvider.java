package com.example.auth_server.services;

import com.example.auth_server.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token-validity}")
    private long accessValidityInMilliseconds;

    /**
     * Genera un token JWT de acceso para el usuario especificado.
     *
     * @param user el usuario autenticado
     * @return el token JWT generado
     */
    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(accessValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene los claims contenidos en el token.
     *
     * @param token JWT válido
     * @return claims extraídos
     */
    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token ha expirado.
     *
     * @param token JWT
     * @return true si el token está expirado, false en caso contrario
     */
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * Retorna la duración restante de validez del token en segundos.
     *
     * @param token JWT
     * @return duración en segundos
     */
    public long getExpirationDuration(String token) {
        Date expirationDate = getClaims(token).getExpiration();
        return (expirationDate.getTime() - System.currentTimeMillis()) / 1000;
    }

    /**
     * Devuelve la clave usada para firmar y verificar JWTs.
     */
    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
