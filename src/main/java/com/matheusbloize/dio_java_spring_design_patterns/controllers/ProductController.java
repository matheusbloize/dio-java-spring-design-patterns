package com.matheusbloize.dio_java_spring_design_patterns.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusbloize.dio_java_spring_design_patterns.dtos.ProductDto;
import com.matheusbloize.dio_java_spring_design_patterns.models.Product;
import com.matheusbloize.dio_java_spring_design_patterns.models.User;
import com.matheusbloize.dio_java_spring_design_patterns.services.ProductService;
import com.matheusbloize.dio_java_spring_design_patterns.services.UserService;
import com.matheusbloize.dio_java_spring_design_patterns.specifications.ProductSpecification;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private UserService userService;

    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll(@RequestParam(required = false) String sort) {
        Optional<String> sortOpt = Optional.ofNullable(sort);
        if (!sortOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productService.listAll());
        } else {
            boolean sortStyle = sort.equalsIgnoreCase("asc") ? true : false;
            return ResponseEntity.status(HttpStatus.OK).body(productService.listAll(ProductSpecification.sortByCreationDate(sortStyle)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOpt = productService.findById(id);
        if (!productOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productOpt.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setCreationDate(LocalDateTime.now());
        product.setOfferExpirationDate(productDto.expirationDate());
        Optional<User> userOpt = userService.findById(productDto.userId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        product.setUser(userOpt.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductDto productDto) {
        Optional<Product> productOpt = productService.findById(id);
        if (!productOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(productOpt.get().getId());
        product.setCreationDate(productOpt.get().getCreationDate());
        product.setOfferExpirationDate(productDto.expirationDate());
        product.setUser(productOpt.get().getUser());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Product> productOpt = productService.findById(id);
        if (!productOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productService.delete(productOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
    }

}
