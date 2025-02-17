package com.matheusbloize.dio_java_spring_design_patterns.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheusbloize.dio_java_spring_design_patterns.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
