package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.RefreshToken;
import br.com.inventory.mechanicalparts.entities.RefreshTokenRequest;
import br.com.inventory.mechanicalparts.entities.RefreshTokenResponse;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.exceptions.NotFoundException;
import br.com.inventory.mechanicalparts.exceptions.TokenRefreshException;
import br.com.inventory.mechanicalparts.repositories.RefreshTokenRepository;
import br.com.inventory.mechanicalparts.repositories.UserRepository;
import br.com.inventory.mechanicalparts.services.RefreshTokenService;
import br.com.inventory.mechanicalparts.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public RefreshTokenResponse findByToken(RefreshTokenRequest refreshToken) {
        String token = refreshTokenRepository.findByToken(refreshToken.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> tokenService.generateToken(
                        userRepository.findById(((User) user).getId()
                        ).orElseThrow(() -> new NotFoundException("User not found")))
                ).orElseThrow(() -> new TokenRefreshException("Token não encontrado!"));
        return new RefreshTokenResponse(token, refreshToken.getRefreshToken(), LocalDateTime.now().plusMinutes(1).toString());
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).orElse(null));
        refreshToken.setExpiryDate(Instant.now().plusMillis(123456789));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken());
        }

        return token;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public JpaRepository<RefreshToken, Long> getRepository() {
        return this.refreshTokenRepository;
    }
}
