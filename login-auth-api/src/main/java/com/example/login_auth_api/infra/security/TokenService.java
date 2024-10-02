package com.example.login_auth_api.infra.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.login_auth_api.domain.user.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            
            String token = JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(Date.from(this.generateExpirationDate()))  
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro de autenticação");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("Falha na validação do token: " + exception.getMessage());
            return null;
        }
    }

   
    private Instant generateExpirationDate() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
}

}
