package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleAdmin);
}
