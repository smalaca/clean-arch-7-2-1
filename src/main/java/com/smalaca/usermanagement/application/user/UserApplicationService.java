package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserFactory;
import com.smalaca.usermanagement.domain.user.UserRepository;

public class UserApplicationService {
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserDto userDto) {
        User user = new UserFactory(userRepository).create(userDto.getLogin(), userDto.getName(), userDto.getLastName());

        userRepository.save(user);
    }
}
