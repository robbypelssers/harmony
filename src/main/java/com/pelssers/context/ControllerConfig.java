package com.pelssers.context;

import com.pelssers.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Autowired
    private ServicesConfig servicesConfig;

    @Bean
    public UserController userController() {
        return new UserController(servicesConfig.userService());
    }

}
