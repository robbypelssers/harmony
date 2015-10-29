package com.pelssersconsultancy.service;


import com.pelssersconsultancy.domain.rest.User;
import com.pelssersconsultancy.repository.UserRepository;

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
    public Optional<User> findUser(String email) {
        return userRepository.findUser(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
