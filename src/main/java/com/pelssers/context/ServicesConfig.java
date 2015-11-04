package com.pelssers.context;


import com.pelssers.service.users.DefaultUserService;
import com.pelssers.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {


    @Autowired
    private RepositoryConfig repositoryConfig;

    @Bean
    public UserService userService() {
        return new DefaultUserService(repositoryConfig.userRepository());
    }
}
