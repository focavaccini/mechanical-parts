package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
