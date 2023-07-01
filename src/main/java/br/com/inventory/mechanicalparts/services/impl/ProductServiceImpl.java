package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ProductRepository;
import br.com.inventory.mechanicalparts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Product productManaged = getById(idProduct);

        productManaged.setName(Util.nvl(product.getName(), productManaged.getName()));
        //productManaged.setUsedQuantity(Util.nvl(product.getUsedQuantity(), productManaged.getUsedQuantity()));
        productManaged.setValue(Util.nvl(product.getValue(), productManaged.getValue()));
        productManaged.setIdentifyCode(Util.nvl(product.getIdentifyCode(), productManaged.getIdentifyCode()));

        getRepository().save(productManaged);
        //productManaged.setServicePerformed(Util.nvl(servicePerformedRepository.findById(product.getServicePerformed().getId()).get(), productManaged.getServicePerformed()));
    }

    @Override
    public Product updateQuantity(Product product, Integer usedQuantity) {


            Product productManaged = getById(product.getId());
            productManaged.setTotalQuantity(productManaged.getTotalQuantity() - usedQuantity);
            return getRepository().save(productManaged);

//        usedProducts.forEach(product -> {
//            Product productManaged = getById(product.getId());
//            productManaged.setTotalQuantity(productManaged.getTotalQuantity() - product.getUsedQuantity());
//            getRepository().save(productManaged);
//        });

    }

    @Override
    public Product getById(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        return product.orElseThrow(()-> new ObjectNotFound("Object not found! Id " + idProduct + ", Type: " + Product.class.getName()));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
