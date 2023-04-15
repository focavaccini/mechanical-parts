package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IProductController;
import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController extends AbstractController<ProductService> implements IProductController {

    ProductService productService;

    @Override
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = convert(productDTO, Product.class);
        productService.insert(product);
        return convert(product, ProductDTO.class);
    }

    @Override
    public void update(Long idProduct, ProductDTO productDTO) {
        Product product = convert(productDTO, Product.class);
        productService.update(idProduct, product);
    }
}
