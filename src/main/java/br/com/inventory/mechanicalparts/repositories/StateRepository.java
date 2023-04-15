package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findAllByOrderByNameAsc();
}
