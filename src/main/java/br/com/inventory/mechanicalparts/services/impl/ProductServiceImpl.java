package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.repositories.ProductRepository;
import br.com.inventory.mechanicalparts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public Product insert(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void update(Long idProduct, Product product) {
        Product productManaged = productRepository.findById(idProduct).orElseThrow();

        productManaged.setName(Util.nvl(product.getName(), productManaged.getName()));
        productManaged.setUsedQuantity(Util.nvl(product.getUsedQuantity(), productManaged.getUsedQuantity()));
        productManaged.setValue(Util.nvl(product.getValue(), productManaged.getValue()));
        productManaged.setIdentifyCode(Util.nvl(product.getIdentifyCode(), productManaged.getIdentifyCode()));

        getRepository().save(productManaged);
        //productManaged.setServicePerformed(Util.nvl(servicePerformedRepository.findById(product.getServicePerformed().getId()).get(), productManaged.getServicePerformed()));
    }

    @Override
    public void updateQuantity(List<Product> usedProducts) {

        usedProducts.forEach(product -> {
            Product productManaged = productRepository.findById(product.getId()).orElseThrow();
            productManaged.setTotalQuantity(productManaged.getTotalQuantity() - 2);
            getRepository().save(productManaged);
        });

    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
