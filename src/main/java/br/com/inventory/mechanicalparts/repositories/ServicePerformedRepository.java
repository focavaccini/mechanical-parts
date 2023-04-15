package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicePerformedRepository extends JpaRepository<ServicePerformed, Long> {
}
