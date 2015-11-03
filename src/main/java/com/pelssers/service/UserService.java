package com.pelssers.service;


import com.pelssers.domain.UserAlreadyExistsException;
import com.pelssers.domain.rest.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user) throws UserAlreadyExistsException;

    Optional<User> findOne(String email);

    List<User> findAll();
}
