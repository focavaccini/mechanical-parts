package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.controllers.interfaces.IAuthController;
import br.com.inventory.mechanicalparts.dtos.Login;
import br.com.inventory.mechanicalparts.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController implements IAuthController {

   private AuthService authService;

    public AutheticationDetails login(@RequestBody Login login){
        return authService.login(login);
    }

}
