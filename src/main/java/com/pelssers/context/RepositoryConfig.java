package com.pelssers.context;

import com.pelssers.repository.users.InMemoryUserRepository;
import com.pelssers.repository.users.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }
}
