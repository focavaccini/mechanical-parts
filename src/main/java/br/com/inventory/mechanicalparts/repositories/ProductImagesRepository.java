package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

    @Query("SELECT u FROM ProductImages u " +
            "WHERE 1 = 1 " +
            "AND u.product = :product " +
            "AND 1 = 1")
    List<ProductImages> findAllByProduct(Product product);
}
