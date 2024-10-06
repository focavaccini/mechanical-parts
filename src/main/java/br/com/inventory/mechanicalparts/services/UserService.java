package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.entities.enums.EnumRole;

public interface UserService extends IAbstractService<User, Long> {

    User saveUser(String emailUser, EnumRole role);

    String generatePassword(String secretKey);

    void updateChangePassword(String email, String password, String newPassword);

    User findByPassword(String token);

}
