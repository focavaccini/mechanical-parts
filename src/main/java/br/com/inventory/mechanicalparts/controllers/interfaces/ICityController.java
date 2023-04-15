package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CityDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/city")
public interface ICityController {

    @PostMapping
    CityDTO insert(@RequestBody CityDTO cityDTO);

    @PutMapping(value = "/{idCity}")
    void update(@PathVariable Long idCity, @RequestBody CityDTO cityDTO);
}
