package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Product;

import java.util.List;

public interface ProductService extends IAbstractService<Product, Long> {

    Product insert(Product product);

    void update(Long idProduct, Product product);

    void updateQuantity(List<Product> usedProducts);
}
