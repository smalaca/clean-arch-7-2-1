package com.smalaca.usermanagement.domain.user;

public class UserFactory {
    private final UserRepository userRepository;

    public UserFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String login, String name, String lastName) {
        if (userRepository.existsWithLogin(login)) {
            throw new UserExistingException(login);
        }

        return new User(login, new Name(name, lastName));
    }
}
