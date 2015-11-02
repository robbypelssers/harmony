package com.pelssers.repository;


import com.pelssers.domain.rest.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private final static Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        users.put(user.getEmail(), user);
        return user;
    }

    @Override
    public Optional<User> findOne(String email) {
        return Optional.ofNullable(users.get(email));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }
}