package com.pelssers.service;


import com.pelssers.domain.Conflict;
import com.pelssers.domain.ResourceNotFound;
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
    public User findOne(String email) throws ResourceNotFound {
        return userRepository.findOne(email);
    }

    @Override
    public void update(User user) throws ResourceNotFound {
         userRepository.update(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
