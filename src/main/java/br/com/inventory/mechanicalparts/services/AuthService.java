package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.configurations.AutheticationDetails;
import br.com.inventory.mechanicalparts.dtos.Login;

public interface AuthService {

    AutheticationDetails login(Login login);

}
