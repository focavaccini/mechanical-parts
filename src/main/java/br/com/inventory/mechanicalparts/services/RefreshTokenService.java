package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.RefreshToken;
import br.com.inventory.mechanicalparts.entities.RefreshTokenRequest;
import br.com.inventory.mechanicalparts.entities.RefreshTokenResponse;

public interface RefreshTokenService extends IAbstractService<RefreshToken, Long> {

    RefreshTokenResponse findByToken(RefreshTokenRequest token);

    RefreshToken save(RefreshToken refreshToken);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

}
