package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CarDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/client-car")
public interface ICarController {

    @PostMapping
    CarDTO insert(@RequestBody CarDTO carDTO);

    @PutMapping(value = "/{idCar}")
    void update(@PathVariable("idCar") Long idCar, @RequestBody CarDTO carDTO);

    @GetMapping
    List<CarDTO> getAll();

    @GetMapping(value = "/{idCar}")
    CarDTO getById(@PathVariable("idCar") Long idCar);
}
