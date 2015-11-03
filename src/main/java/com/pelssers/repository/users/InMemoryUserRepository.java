package com.pelssers.repository.users;


import com.pelssers.domain.UserAlreadyExistsException;
import com.pelssers.domain.rest.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
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
