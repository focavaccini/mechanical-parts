package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService extends IAbstractService<User, Long> {

    public void saveUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
