package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserFactory;
import com.smalaca.usermanagement.domain.user.UserRepository;

import java.util.UUID;

public class UserApplicationService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    UserApplicationService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public UUID register(UserDto userDto) {
        User user = userFactory.create(userDto.getLogin(), userDto.getName(), userDto.getLastName());

        return userRepository.save(user);
    }
}
