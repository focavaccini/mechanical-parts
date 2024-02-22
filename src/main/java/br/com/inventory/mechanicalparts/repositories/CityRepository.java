package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByState(State state);

    @Query("SELECT u FROM City u " +
            "WHERE 1 = 1 " +
            "AND upper(u.name) LIKE upper(CONCAT('%', ?1 ,'%'))" +
            "AND  1 = 1")
    List<City> findAllByName(String name);
}
