package com.pelssers.service;


import com.pelssers.domain.Conflict;
import com.pelssers.domain.NotFound;
import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;

import java.util.*;

public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws Conflict {
        return userRepository.createUser(user);
    }

    @Override
    public User findOne(String email) throws NotFound {
        return userRepository.findOne(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
