package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.dtos.CarDTO;
import br.com.inventory.mechanicalparts.entities.Car;

public interface CarService extends IAbstractService<Car, Long> {

    Car insert(Car car);

    void update(Long idCar, Car car);

//    CarDTO convertToDTO(Car car);
//    Car convertToEntity(CarDTO carDTO);
}
