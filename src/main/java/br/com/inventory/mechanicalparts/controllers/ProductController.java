package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IProductController;
import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import br.com.inventory.mechanicalparts.dtos.ProductImagesDTO;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import br.com.inventory.mechanicalparts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public void insertImage(Long idProduct, MultipartFile multipartFile) {
        productService.insertImage(idProduct, multipartFile);
    }

    @Override
    public void update(Long idProduct, ProductDTO productDTO) {
        Product product = convert(productDTO, Product.class);
        productService.update(idProduct, product);
    }

    @Override
    public List<ProductDTO> getAll() {
        return convert(productService.getAll(), ProductDTO.class);
    }

    @Override
    public ProductDTO getById(Long idProduct) {
        return convert(productService.getById(idProduct), ProductDTO.class);
    }

    @Override
    public List<ProductImages> getAllByProduct(Long idProduct) {
        return convert(productService.getAllByProduct(idProduct), ProductImagesDTO.class);
    }
}
