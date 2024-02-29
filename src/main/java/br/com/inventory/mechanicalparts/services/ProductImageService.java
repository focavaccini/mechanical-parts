package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService extends IAbstractService<ProductImages, Long> {

    void insert(ProductImages productImages, MultipartFile multipartFile);

    void update(Long idProductImages, ProductImages productImages);

    ProductImages getById(Long idProductImages);

    List<ProductImages> getAllByProduct(Product product);

}
