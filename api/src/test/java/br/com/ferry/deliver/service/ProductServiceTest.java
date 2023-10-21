package br.com.ferry.deliver.service;

import br.com.ferry.deliver.model.Product;
import br.com.ferry.deliver.model.dto.ProductDto;
import br.com.ferry.deliver.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startProducts();
    }

    @Test
    void findAll() {
        when(productRepository.findAllByNameNotNullOrderByNameAsc()).thenReturn(List.of(product));

        List<ProductDto> resp = productService.findAll();
        assertEquals(ProductDto.class, resp.get(0).getClass());
    }

    private void startProducts() {
        this.product = Product.builder()
                .id(1L)
                .name("name-name-name")
                .price(999.9)
                .description("description-description-description")
                .imageUri("fggfdretredgfhghfvbvcvcbcvb")
                .build();
    }
}