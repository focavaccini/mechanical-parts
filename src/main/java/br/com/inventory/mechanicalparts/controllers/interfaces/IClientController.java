package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CarDTO;
import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/client")
public interface IClientController {

    @PostMapping
    ClientDTO insert(@RequestBody ClientDTO clientDTO);

    @PutMapping(value = "/{idClient}")
    void update(@PathVariable Long idClient, @RequestBody ClientDTO clientDTO);

    @GetMapping(value="/{idClient}")
    ClientDTO getById(@PathVariable("idClient") Long idClient);

    @GetMapping(value = "/listAll")
    List<ClientDTO> getAll();

    @PostMapping(value = "/insertCar/{idClient}")
    CarDTO insertCar(@PathVariable Long idClient, @RequestBody CarDTO carDTO);

    @PutMapping(value = "/updateCar/{idClient}/idCar/{idCar}")
    void updateCar(@PathVariable Long idClient, @PathVariable Long idCar, @RequestBody CarDTO carDTO);
}
