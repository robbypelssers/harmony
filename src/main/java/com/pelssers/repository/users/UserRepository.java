package com.pelssers.repository.users;


import com.pelssers.domain.Conflict;
import com.pelssers.domain.ResourceNotFound;
import com.pelssers.domain.rest.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user) throws Conflict;

    User findOne(String email) throws ResourceNotFound;

    void update(User user) throws ResourceNotFound;

    List<User> findAll();
}
