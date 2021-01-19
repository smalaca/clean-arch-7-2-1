package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.UserFactory;
import com.smalaca.usermanagement.domain.user.UserRepository;

public class UserApplicationServiceFactory {
    public UserApplicationService userApplicationService(UserRepository userRepository) {
        return new UserApplicationService(userRepository, new UserFactory(userRepository));
    }
}
