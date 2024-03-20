package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Category;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ProductRepository;
import br.com.inventory.mechanicalparts.services.CategoryService;
import br.com.inventory.mechanicalparts.services.ProductImageService;
import br.com.inventory.mechanicalparts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ProductImageService productImageService;

    private CategoryService categoryService;

    @Override
    public Product insert(Product product) {
        Category category = categoryService.findByName(product.getCategory().getName());
        product.setCategory(category);
        product.setActive(true);
        product.setRegistrationDate(LocalDateTime.now());
        onPrepareInsertOrUpdate(product);
        return productRepository.save(product);
    }

    @Override
    public void insertImage(Long idProduct, MultipartFile multipartFile) {
        ProductImages productImages = new ProductImages();
        Product product = getById(idProduct);
        productImages.setProduct(product);
        productImageService.insert(productImages, multipartFile);
    }

    @Override
    public void update(Long idProduct, Product product) {
        Product productManaged = getById(idProduct);
        productManaged.setUpdateDate(LocalDateTime.now());
        productManaged.setName(Util.nvl(product.getName(), productManaged.getName()));
        productManaged.setValue(Util.nvl(product.getValue(), productManaged.getValue()));
        productManaged.setCategory(Util.nvl(product.getCategory(), productManaged.getCategory()));
        productManaged.setIdentifyCode(Util.nvl(product.getIdentifyCode(), productManaged.getIdentifyCode()));
        getRepository().save(productManaged);
    }

    @Override
    public Product updateQuantity(Product product, Integer usedQuantity) {
        Product productManaged = getById(product.getId());
        productManaged.setTotalQuantity(productManaged.getTotalQuantity() - usedQuantity);

        return getRepository().save(productManaged);
    }

    @Override
    public Product getById(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        return product.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idProduct + ", Type: " + Product.class.getName()));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductImages> getAllByProduct(Long idProduct) {
        Product product = getById(idProduct);
        return productImageService.getAllByProduct(product);
    }

    private void onPrepareInsertOrUpdate(Product product) {
        checkIfIdentifyCodeExists(product);
    }

    private void checkIfIdentifyCodeExists(Product product) {
        Product carManaged = productRepository.findByIdentifyCode(product.getIdentifyCode());
        if (carManaged != null && !carManaged.getId().equals(product.getId())) {
            throw new BadRequestException("Código identifyCode já pertence a outro produto");
        }
    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
