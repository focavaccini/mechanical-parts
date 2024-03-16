package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Professional findByEmail(String email);

    Professional findByPhone(String phone);

    Professional findByCode(String code);

    @Query("SELECT p " +
            " FROM Professional p " +
            " WHERE 1 = 1 " +
            " AND upper(p.name) = upper(?1)" +
            " AND 1 = 1 ")
    Professional findByName(String name);

    @Query("SELECT p " +
            " FROM Professional p " +
            " WHERE 1 = 1 " +
            " AND p.name LIKE CONCAT('%', ?1,'%')" +
            " AND 1 = 1 ")
    List<Professional> findAllByName(String name);

    Professional findByUserId(Long userId);
}
