package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CarDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client-car")
public interface ICarController {

    @PostMapping
    CarDTO insert(@RequestBody CarDTO carDTO);

    @PutMapping(value = "/{idCar}")
    void update(@PathVariable Long idCar, @RequestBody CarDTO carDTO);

}
