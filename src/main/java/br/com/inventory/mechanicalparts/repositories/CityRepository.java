package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
