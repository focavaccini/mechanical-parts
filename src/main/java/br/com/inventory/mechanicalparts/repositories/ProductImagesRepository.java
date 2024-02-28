package br.com.inventory.mechanicalparts.repositories;

import br.com.inventory.mechanicalparts.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
}
