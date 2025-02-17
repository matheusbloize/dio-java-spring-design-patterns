package com.matheusbloize.dio_java_spring_design_patterns.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.matheusbloize.dio_java_spring_design_patterns.models.User;
import com.matheusbloize.dio_java_spring_design_patterns.repositories.UserRepository;
import com.matheusbloize.dio_java_spring_design_patterns.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    // Singleton Pattern
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Strategy and Facade Patterns

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User getOne(UUID id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

}
