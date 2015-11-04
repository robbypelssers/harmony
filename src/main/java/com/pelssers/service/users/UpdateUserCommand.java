package com.pelssers.service.users;


import com.pelssers.async.FailableCommand;
import com.pelssers.domain.ResourceNotFound;
import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;

public class UpdateUserCommand implements FailableCommand<Void, ResourceNotFound> {

    private final UserRepository userRepository;
    private final User user;

    public UpdateUserCommand(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    @Override
    public Void execute() throws ResourceNotFound {
        userRepository.update(user);
        return null;
    }
}
