package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT u FROM Category u " +
            " WHERE 1 = 1 " +
            "AND upper(u.name) = upper(?1) " +
            "AND 1 = 1")
    Category findByName(String name);

    @Query("SELECT u FROM Category u " +
            " WHERE 1 = 1 " +
            "AND upper(u.code) = upper(?1) " +
            "AND 1 = 1")
    Category findByCode(String code);
}
