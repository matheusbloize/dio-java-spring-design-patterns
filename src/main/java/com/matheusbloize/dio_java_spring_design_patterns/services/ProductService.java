package com.matheusbloize.dio_java_spring_design_patterns.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;

// Strategy Pattern
public interface ProductService {
    List<Product> listAll();

    List<Product> listAll(Specification<Product> filter);

    Optional<Product> findById(UUID id);

    Set<Product> findByUser(UUID id);

    Product getOne(UUID id);

    Product save(Product product);

    void delete(Product product);

    void deleteOnExpiration();

}
