package com.pelssers.repository;


import com.pelssers.domain.rest.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User createUser(User user);

    Optional<User> findOne(String email);

    List<User> findAll();
}
