package com.pelssers.context;

import com.pelssers.controller.UserApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Autowired
    private ServicesConfig servicesConfig;

    @Bean
    public UserApiController userController() {
        return new UserApiController(servicesConfig.userService());
    }

}
