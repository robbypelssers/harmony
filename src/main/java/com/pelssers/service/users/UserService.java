package com.pelssers.service.users;

import com.pelssers.domain.rest.User;


public interface UserService {

    FindUserCommand findUser(String email);

    CreateUserCommand createUser(User user);

    GetUsersCommand getUsers();

    UpdateUserCommand updateUser(User user);
}
