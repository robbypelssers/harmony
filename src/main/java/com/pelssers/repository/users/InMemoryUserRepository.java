package com.pelssers.repository.users;


import com.pelssers.context.ApiMessages;
import com.pelssers.domain.Conflict;
import com.pelssers.domain.ResourceNotFound;
import com.pelssers.domain.rest.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository, ApiMessages {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(User user) throws Conflict {
        if (users.containsKey(user.getEmail())) {
            throw new Conflict(String.format(USER_ALREADY_EXISTS, user.getEmail()));
        }
        user.setId(UUID.randomUUID().toString());
        users.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User findOne(String email) throws ResourceNotFound {
        if (users.containsKey(email)) {
            return users.get(email);
        }
        throw new ResourceNotFound(String.format(USER_NOT_FOUND, email));
    }

    @Override
    public void update(User user) throws ResourceNotFound {
        User existingUser = findOne(user.getEmail());
        existingUser.setBirthDay(user.getBirthDay());
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }
}
