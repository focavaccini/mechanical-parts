package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.repositories.ProductRepository;
import br.com.inventory.mechanicalparts.services.CategoryService;
import br.com.inventory.mechanicalparts.services.ProductImageService;
import br.com.inventory.mechanicalparts.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductImageService productImageService;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        Product product = new Product();
        product.setCategory(new Category());
        Category category = new Category();
        when(categoryService.findByName(product.getCategory().getName())).thenReturn(category);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.insert(product);
        assertNotNull(result);
        assertEquals(category, result.getCategory());
        assertTrue(result.getActive());
        assertNotNull(result.getRegistrationDate());
        verify(productRepository).save(product);
    }

    @Test
    void testInsertImage() {
        Long productId = 1L;
        MultipartFile file = mock(MultipartFile.class);
        Product product = new Product();
        ProductImages productImages = new ProductImages();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        doNothing().when(productImageService).insert(any(ProductImages.class), eq(file));

        productService.insertImage(productId, file);

        verify(productImageService).insert(any(ProductImages.class), eq(file));
    }

    @Test
    void testUpdateQuantity() {
        Product product = new Product();
        product.setTotalQuantity(10);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateQuantity(product, 5);

        assertEquals(5, result.getTotalQuantity());
        assertTrue(result.getActive());
        verify(productRepository).save(product);
    }
}
