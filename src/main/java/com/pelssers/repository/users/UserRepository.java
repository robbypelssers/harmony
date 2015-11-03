package com.pelssers.repository.users;


import com.pelssers.domain.Conflict;
import com.pelssers.domain.NotFound;
import com.pelssers.domain.rest.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user) throws Conflict;

    User findOne(String email) throws NotFound;

    List<User> findAll();
}
