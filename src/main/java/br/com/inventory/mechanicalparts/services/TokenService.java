package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.entities.User;

public interface TokenService {
     String generateToken(User user);

     String getSubject(String token);
}
