package com.matheusbloize.dio_java_spring_design_patterns.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;
import com.matheusbloize.dio_java_spring_design_patterns.repositories.ProductRepository;
import com.matheusbloize.dio_java_spring_design_patterns.services.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    // Singleton Pattern
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Strategy and Facade Patterns

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listAll(Specification<Product> sortByCreationDate) {
        return productRepository.findAll(sortByCreationDate);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public Set<Product> findByUser(UUID id) {
        return productRepository.findById(id).get().getUser().getProducts();
    }

    @Override
    public Product getOne(UUID id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
