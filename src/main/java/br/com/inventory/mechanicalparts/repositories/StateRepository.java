package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findAllByOrderByNameAsc();

    State findByNameIgnoreCase(String name);

    @Query("SELECT u FROM State u " +
            " WHERE 1 = 1 " +
            "AND upper(u.name) = upper(?1) " +
            "AND 1 = 1")
    State findByName(String name);
}
