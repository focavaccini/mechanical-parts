package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.controllers.interfaces.IAuthController;
import br.com.inventory.mechanicalparts.dtos.Login;
import br.com.inventory.mechanicalparts.entities.RefreshTokenRequest;
import br.com.inventory.mechanicalparts.entities.RefreshTokenResponse;
import br.com.inventory.mechanicalparts.services.AuthService;
import br.com.inventory.mechanicalparts.services.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements IAuthController {

    private AuthService authService;

    private RefreshTokenService refreshTokenService;

    public AutheticationDetails login(@RequestBody Login login) {
        return authService.login(login);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshToken) {
        return refreshTokenService.findByToken(refreshToken);
//        return refreshTokenService.findByToken(refreshToken.getRefreshToken());
    }
}
