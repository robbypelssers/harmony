package com.pelssers.service.users;

import com.pelssers.domain.rest.User;
import com.pelssers.repository.users.UserRepository;


public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FindUserCommand findUser(String email) {
        return new FindUserCommand(userRepository, email);
    }

    @Override
    public CreateUserCommand createUser(User user) {
        return new CreateUserCommand(userRepository, user);
    }

    @Override
    public GetUsersCommand getUsers() {
        return new GetUsersCommand(userRepository);
    }

    @Override
    public UpdateUserCommand updateUser(User user) {
        return new UpdateUserCommand(userRepository, user);
    }
}
