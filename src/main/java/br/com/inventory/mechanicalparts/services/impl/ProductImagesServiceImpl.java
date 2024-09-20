package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Product;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.FileException;
import br.com.inventory.mechanicalparts.repositories.ProductImagesRepository;
import br.com.inventory.mechanicalparts.services.ProductImageService;
import br.com.inventory.mechanicalparts.services.S3Service;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        isValidTypeImage(multipartFile);

        BufferedImage jpgImage = getJpgImageFromFile(multipartFile);
        int size = 300;
        String prefix = "pd-";

        jpgImage = cropSquare(jpgImage);
        jpgImage = resize(jpgImage, size);

        String fileName = prefix + productImages.getProduct().getIdentifyCode() + ".jpg";

        URI uri = s3Service.uploadFile(getInputStream(jpgImage, "jpg"), fileName, "image");
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

    @Override
    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {

        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());

            if (image.equals("png")) {
                image = pngToJpg(image);
            }

            return image;

        } catch (IOException e) {
            throw new FileException("Error reading file");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extension) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, extension, outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new FileException("Error reading file");
        }
    }

    public BufferedImage cropSquare(BufferedImage sourceImg) {
        int min = Math.min(sourceImg.getHeight(), sourceImg.getWidth());
        return Scalr.crop(
                sourceImg,
                (sourceImg.getWidth() / 2) - (min / 2),
                (sourceImg.getHeight() / 2) - (min / 2),
                min,
                min);
    }

    public BufferedImage resize(BufferedImage sourceImg, int size) {
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }

    public void isValidTypeImage(MultipartFile multipartFile) {
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if (Util.isEmpty(fileExtension)) {
            throw new BadRequestException("Image format invalid/empty");
        }

        if (!fileExtension.equals("png") && !fileExtension.equals("jpg")) {
            throw new FileException("Image Format Invalid");
        }
    }

    @Override
    public JpaRepository<ProductImages, Long> getRepository() {
        return this.productImagesRepository;
    }
}
