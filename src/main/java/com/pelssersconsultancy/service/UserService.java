package com.pelssersconsultancy.service;


import com.pelssersconsultancy.domain.rest.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> findUser(String email);

    List<User> findAll();
}
