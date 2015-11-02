package com.pelssers.service;


import com.pelssers.domain.rest.User;
import com.pelssers.repository.UserRepository;

import java.util.*;

public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public Optional<User> findOne(String email) {
        return userRepository.findOne(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
