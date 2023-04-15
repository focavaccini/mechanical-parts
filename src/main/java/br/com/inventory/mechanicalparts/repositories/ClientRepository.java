package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Client findByPhone(String phone);

    Client findByCpf(String cpf);

}
