package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.repositories.ProductRepository;
import br.com.inventory.mechanicalparts.repositories.ServicePerformedRepository;
import br.com.inventory.mechanicalparts.services.ProductService;
import br.com.inventory.mechanicalparts.services.ServicePerformedService;
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

    //    @Override
//    public ProductDTO convertToDTO(Product product) {
//        ProductDTO productDTO = new ProductDTO();
//
//        productDTO.setValue(product.getValue());
//        productDTO.setName(product.getName());
//        productDTO.setQuantity(product.getQuantity());
//        productDTO.setIdentifyCode(product.getIdentifyCode());
//
//        return productDTO;
//    }
//
//    @Override
//    public Product converterToEntity(ProductDTO productDTO) {
//        Product product = new Product();
//
//        product.setValue(productDTO.getValue());
//        product.setName(productDTO.getName());
//        product.setQuantity(productDTO.getQuantity());
//        product.setIdentifyCode(productDTO.getIdentifyCode());
//
//        return product;
//    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
