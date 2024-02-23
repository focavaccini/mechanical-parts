package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Car;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.CarRepository;
import br.com.inventory.mechanicalparts.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Override
    public Car insert(Car car) {
        onPrepareInsertOrUpdate(car);
        car.setRegistrationDate(LocalDateTime.now());
        carRepository.save(car);

        return car;
    }

    @Override
    public void update(Long idCar, Car car) {
        Car carManaged = getById(idCar);
        carManaged.setUpdateDate(LocalDateTime.now());
        carManaged.setModel(Util.nvl(car.getModel(), carManaged.getModel()));
        carManaged.setFuel(Util.nvl(car.getFuel(), carManaged.getFuel()));
        carManaged.setColor(Util.nvl(car.getColor(), carManaged.getColor()));
        carManaged.setYearOfManufacture(Util.nvl(car.getYearOfManufacture(), carManaged.getYearOfManufacture()));
        carManaged.setLicensePlate(Util.nvl(car.getLicensePlate(), carManaged.getLicensePlate()));
        onPrepareInsertOrUpdate(carManaged);
        carRepository.save(carManaged);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car getById(Long idCar) {
        Optional<Car> car = carRepository.findById(idCar);
        return car.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idCar + ", Type: " + Car.class.getName()));
    }

    private void onPrepareInsertOrUpdate(Car car) {
        checkIfLicensePlateExists(car);
    }

    private void checkIfLicensePlateExists(Car car){
        Car carManaged = carRepository.findByLicensePlate(car.getLicensePlate());
        if(carManaged != null && !carManaged.getId().equals(car.getId())){
            throw new BadRequestException("A placa informada pertence a outro veículo");
        }
    }

    @Override
    public JpaRepository<Car, Long> getRepository() {
        return this.carRepository;
    }
}
