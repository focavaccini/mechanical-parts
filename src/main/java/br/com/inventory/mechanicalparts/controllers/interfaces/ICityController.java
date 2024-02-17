package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CityDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/city")
public interface ICityController {

    @PostMapping
    CityDTO insert(@RequestBody CityDTO cityDTO);

    @PutMapping(value = "/{idCity}")
    void update(@PathVariable Long idCity, @RequestBody CityDTO cityDTO);

    @GetMapping(value = "/{idCity}")
    CityDTO getById(@PathVariable Long idCity);

    @GetMapping(value = "/state/{nameStAate}")
    List<CityDTO> getByStateName(@PathVariable String namaState);

    @GetMapping
    List<CityDTO> getAll();
}
