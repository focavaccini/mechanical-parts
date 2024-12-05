package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.dtos.Login;
import br.com.inventory.mechanicalparts.entities.RefreshTokenRequest;
import br.com.inventory.mechanicalparts.entities.RefreshTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public interface IAuthController {

    @PostMapping("/login")
    AutheticationDetails login(@RequestBody Login login);

    @PostMapping("/refreshToken")
    RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken);

}
