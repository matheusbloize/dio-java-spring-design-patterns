package com.matheusbloize.dio_java_spring_design_patterns.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.matheusbloize.dio_java_spring_design_patterns.models.User;

// Strategy Pattern
public interface UserService {

    List<User> listAll();

    Optional<User> findById(UUID id);

    User getOne(UUID id);

    User save(User user);

    void delete(User user);
}
