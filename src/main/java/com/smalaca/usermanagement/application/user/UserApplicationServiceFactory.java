package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.UserFactory;
import com.smalaca.usermanagement.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserApplicationServiceFactory {
    @Bean
    public UserApplicationService userApplicationService(UserRepository userRepository) {
        return new UserApplicationService(userRepository, new UserFactory(userRepository));
    }
}
