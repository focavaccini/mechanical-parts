package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
