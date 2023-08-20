package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IUserController;
import br.com.inventory.mechanicalparts.dtos.UserDTO;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController extends AbstractController<UserService> implements IUserController {

    private UserService userService;

    @Override
    public void update(Object obj) {

        userService.updateChangePassword((String) ((LinkedHashMap) obj).get("login"), (String) ((LinkedHashMap) obj).get("password"), (String) ((LinkedHashMap) obj).get("newPassword"));
    }

    @Override
    public List<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO getById(Long idUser) {
        return null;
    }
}
