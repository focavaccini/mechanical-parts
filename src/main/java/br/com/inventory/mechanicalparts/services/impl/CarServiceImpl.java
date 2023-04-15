package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Car;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.repositories.CarRepository;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.CarService;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private ClientService clientService;

    private ClientRepository clientRepository;

    @Override
    public Car insert(Car car) {
        carRepository.save(car);
        Client client = clientService.buscarPorId(1L);
        client.getCars().add(car);
        return car;
    }

    @Override
    public void update(Long idCar, Car car) {
        Car carManaged = carRepository.findById(idCar).orElseThrow();
        carManaged.setModel(Util.nvl(car.getModel(), carManaged.getModel()));
        carManaged.setFuel(Util.nvl(car.getFuel(), carManaged.getFuel()));
        carManaged.setColor(Util.nvl(car.getColor(), carManaged.getColor()));
        carManaged.setYearOfManufacture(Util.nvl(car.getYearOfManufacture(), carManaged.getYearOfManufacture()));
        carManaged.setLicensePlate(Util.nvl(car.getLicensePlate(), carManaged.getLicensePlate()));
        carManaged.setClient(Util.nvl(clientRepository.findById(car.getClient().getId()).get(), carManaged.getClient()));

        carRepository.save(carManaged);
    }

    @Override
    public JpaRepository<Car, Long> getRepository() {
        return this.carRepository;
    }
}
