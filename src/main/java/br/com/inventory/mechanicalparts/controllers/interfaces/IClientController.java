package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/client")
public interface IClientController {

    @PostMapping
    ClientDTO insert(@RequestBody ClientDTO clientDTO);

    @PutMapping(value = "/{idClient}")
    void update(@PathVariable Long idClient, @RequestBody ClientDTO clientDTO);

    @GetMapping(value="/{idClient}")
    ClientDTO buscarPorId(@PathVariable("idClient") Long idClient);
}
