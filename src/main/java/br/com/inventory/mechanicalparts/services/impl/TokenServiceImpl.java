package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.services.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(User user){
        return JWT.create()
                .withIssuer("Professional")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secret"));
    }
    @Override
    public String getSubject(String token){
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("Professional")
                .build()
                .verify(token).getSubject();
    }
}
