package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import br.com.inventory.mechanicalparts.repositories.ProductImagesRepository;
import br.com.inventory.mechanicalparts.services.ProductImageService;
import br.com.inventory.mechanicalparts.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductImagesServiceImpl implements ProductImageService {

    private ProductImagesRepository productImagesRepository;

    private S3Service s3Service;

    @Override
    public void insert(ProductImages productImages, MultipartFile multipartFile) {
        URI uri = s3Service.uploadFile(multipartFile);
        productImages.setPath(String.valueOf(uri));
        productImages.setRegistrationDate(LocalDateTime.now());
        productImagesRepository.save(productImages);
    }

    @Override
    public void update(Long idProductImages, ProductImages productImages) {

    }

    @Override
    public ProductImages getById(Long idProductImages) {
        return null;
    }

    @Override
    public List<ProductImages> getAllByProduct(Product product) {
        return productImagesRepository.findAllByProduct(product);
    }

    private URI uploadProductImages(MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }

    @Override
    public JpaRepository<ProductImages, Long> getRepository() {
        return this.productImagesRepository;
    }
}
