package br.com.ferry.deliver.repository;

import br.com.ferry.deliver.model.Product;
import br.com.ferry.deliver.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameNotNullOrderByNameAsc();
}