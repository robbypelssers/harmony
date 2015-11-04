package com.pelssers.service.users;

import com.pelssers.async.FailableCommand;
import com.pelssers.domain.ResourceNotFound;
import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;


public class FindUserCommand implements FailableCommand<User, ResourceNotFound> {

    private final UserRepository userRepository;
    private final String email;

    public FindUserCommand(UserRepository userRepository, String email) {
        this.userRepository = userRepository;
        this.email = email;
    }

    @Override
    public User execute() throws ResourceNotFound {
        return userRepository.findOne(email);
    }
}
