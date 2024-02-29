package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends IAbstractService<Product, Long> {

    Product insert(Product product);

    void insertImage(Long idProduct, MultipartFile multipartFile);

    void update(Long idProduct, Product product);

    Product updateQuantity(Product product, Integer usedQuantity);

    Product getById(Long idProduct);

    List<Product> getAll();

    List<ProductImages> getAllByProduct(Long idProduct);
}
