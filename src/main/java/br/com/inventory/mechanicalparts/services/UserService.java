package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.User;

public interface UserService extends IAbstractService<User, Long> {

    User saveUser(String typeUser, String emailUser);

    String generatePassword(String secretKey);

    void updateChangePassword(String email, String password, String newPassword);

    User findByPasswordAndLogin(String token, String login);

}
