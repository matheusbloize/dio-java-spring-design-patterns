package com.matheusbloize.dio_java_spring_design_patterns.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
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
    public List<Product> listAll(Specification<Product> filter) {
        return productRepository.findAll(filter);
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

    @Override
    @Transactional
    @Scheduled(cron = "0 0 12 * * ?")
    public void deleteOnExpiration() {
        // Delete all products that have expired by the offer date at noon every day
        List<Product> allProducts = listAll();
        LocalDateTime date = LocalDateTime.now();
        allProducts.forEach(prod -> {
            if (date.compareTo(prod.getOfferExpirationDate()) > 0) {
                delete(prod);
            }
        });
    }
}
