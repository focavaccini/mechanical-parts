package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.ProductImages;

import java.util.List;

public interface ProductImageService extends IAbstractService<ProductImages, Long> {

    ProductImages insert(ProductImages productImages);

    void update(Long idProductImages, ProductImages productImages);

    ProductImages getById(Long idProductImages);

    List<ProductImages> getAll();

}
