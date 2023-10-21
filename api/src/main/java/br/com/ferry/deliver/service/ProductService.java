package br.com.ferry.deliver.service;

import br.com.ferry.deliver.model.dto.ProductDto;
import br.com.ferry.deliver.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productRepository.findAllByNameNotNullOrderByNameAsc().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
