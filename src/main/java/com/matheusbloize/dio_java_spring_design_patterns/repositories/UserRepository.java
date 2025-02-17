package com.matheusbloize.dio_java_spring_design_patterns.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheusbloize.dio_java_spring_design_patterns.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
