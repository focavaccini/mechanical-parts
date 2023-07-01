package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.dtos.Login;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    @Override
    public AutheticationDetails login(Login login) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.login(), login.password());

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();

        AutheticationDetails autheticationDetails = new AutheticationDetails();
        autheticationDetails.setToken(tokenServiceImpl.generateToken(user));

        return autheticationDetails;
    }
}
