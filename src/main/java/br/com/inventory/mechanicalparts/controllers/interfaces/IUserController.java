package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface IUserController {

    @PutMapping
    void update(@RequestBody Object obj);

    @GetMapping
    List<UserDTO> getAll();

    @GetMapping(value = "/{idUser}")
    UserDTO getById(@PathVariable("idUser") Long idUser);
}
