package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Professional findByEmail(String email);

    Professional findByPhone(String phone);
}
