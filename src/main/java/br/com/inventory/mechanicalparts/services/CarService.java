package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Car;

import java.util.List;

public interface CarService extends IAbstractService<Car, Long> {

    Car insert(Car car);

    void update(Long idCar, Car car);

    List<Car> getAll();

    Car getById(Long idCar);
}
