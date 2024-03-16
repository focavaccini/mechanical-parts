package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicePerformedRepository extends JpaRepository<ServicePerformed, Long> {


    List<ServicePerformed> findAllByProfessionalId(Long professionalId);
}
