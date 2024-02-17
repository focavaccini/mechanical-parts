package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByState(State state);
}
