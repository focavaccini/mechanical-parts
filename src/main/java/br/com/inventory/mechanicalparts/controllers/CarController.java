package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.ICarController;
import br.com.inventory.mechanicalparts.dtos.CarDTO;
import br.com.inventory.mechanicalparts.entities.Car;
import br.com.inventory.mechanicalparts.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CarController extends AbstractController<CarService> implements ICarController {

    private CarService carService;

    @PostMapping
    public CarDTO insert(@RequestBody CarDTO carDTO){
        Car car = convert(carDTO, Car.class);
        carService.insert(car);
        return convert(car, CarDTO.class);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Long idCar, @RequestBody CarDTO carDTO){
        Car car = convert(carDTO, Car.class);
        carService.update(idCar, car);
    }


}
