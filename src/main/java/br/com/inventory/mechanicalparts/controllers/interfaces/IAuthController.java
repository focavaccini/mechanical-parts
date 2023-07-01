package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.dtos.Login;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public interface IAuthController {

    @PostMapping("/login")
    AutheticationDetails login(@RequestBody Login login);

}
