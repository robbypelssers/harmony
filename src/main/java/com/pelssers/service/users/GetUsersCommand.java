package com.pelssers.service.users;


import com.pelssers.async.Command;
import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;

import java.util.List;

public class GetUsersCommand implements Command<List<User>> {

    private final UserRepository userRepository;

    public GetUsersCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute() {
        return userRepository.findAll();
    }
}
