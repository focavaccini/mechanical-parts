package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE LOWER(c.licensePlate) = LOWER(:licensePlate)")
    Car findByLicensePlate(@Param("licensePlate") String licensePlate);

}
