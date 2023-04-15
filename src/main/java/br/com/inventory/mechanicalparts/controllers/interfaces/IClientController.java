package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import br.com.inventory.mechanicalparts.entities.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/client")
public interface IClientController {

    @PostMapping
    ClientDTO insert(@RequestBody ClientDTO clientDTO);

    @PutMapping(value = "/{id}")
    void update(@PathVariable Long idClient, @RequestBody ClientDTO clientDTO);

    @GetMapping(value="/{id}")
    ClientDTO buscarPorId(@PathVariable("id") Long idClient);
}
