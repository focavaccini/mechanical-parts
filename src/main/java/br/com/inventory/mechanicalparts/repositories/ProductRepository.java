package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.identifyCode) = LOWER(?1)")
    Product findByIdentifyCode(String identifyCode);
}
