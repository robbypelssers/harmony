package com.pelssers.service.users;

import com.pelssers.async.FailableCommand;
import com.pelssers.domain.Conflict;
import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;


public class CreateUserCommand implements FailableCommand<User, Conflict> {

    private final UserRepository userRepository;
    private final User user;

    public CreateUserCommand(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    @Override
    public User execute() throws Conflict {
        return userRepository.createUser(user);
    }
}
