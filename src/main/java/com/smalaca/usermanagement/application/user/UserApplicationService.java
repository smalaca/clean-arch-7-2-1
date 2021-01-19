package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserRepository;

public class UserApplicationService {
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserDto userDto) {
        User user = User.Builder.user()
                .withLogin(userDto.getLogin())
                .withName(userDto.getName(), userDto.getLastName())
                .build();

        userRepository.save(user);
    }
}
