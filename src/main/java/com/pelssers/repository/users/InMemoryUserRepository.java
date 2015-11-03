package com.pelssers.repository.users;


import com.pelssers.context.ApiExceptionMessages;
import com.pelssers.domain.Conflict;
import com.pelssers.domain.NotFound;
import com.pelssers.domain.rest.User;

import java.util.*;

public class InMemoryUserRepository implements UserRepository, ApiExceptionMessages {

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
    public User findOne(String email) throws NotFound {
        if (users.containsKey(email)) {
            return users.get(email);
        }
        throw new NotFound(String.format(USER_NOT_FOUND, email));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }
}
