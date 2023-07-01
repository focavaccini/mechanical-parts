package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String username);
}
