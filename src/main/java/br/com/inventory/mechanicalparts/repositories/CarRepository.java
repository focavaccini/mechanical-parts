package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
