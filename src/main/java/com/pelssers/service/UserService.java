package com.pelssers.service;


import com.pelssers.domain.Conflict;
import com.pelssers.domain.NotFound;
import com.pelssers.domain.rest.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user) throws Conflict;

    User findOne(String email) throws NotFound;

    List<User> findAll();
}
