package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
