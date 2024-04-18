package com.example.WhatsApp_Clone_API.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;



import com.auth0.jwt.algorithms.Algorithm;
import com.example.WhatsApp_Clone_API.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("123456")
    private String secret;

    public String gerarToken(User user){
            try{
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.create()
                        .withIssuer("API ZAP 2")
                        .withSubject(user.getPhone_number())
                        .withExpiresAt(dataExpliracao())
                        .sign(algoritmo);
            } catch (JWTCreationException exception){
                throw  new RuntimeException("Erro ao gerar token jwt", exception);
            }

    }

    private Instant dataExpliracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJwt){
        DecodedJWT decodedJWT;
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API ZAP 2")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }
}
