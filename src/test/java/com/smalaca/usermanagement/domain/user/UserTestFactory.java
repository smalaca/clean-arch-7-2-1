package com.smalaca.usermanagement.domain.user;

public class UserTestFactory {
    public User create(String login, String name, String lastName) {
        return new User(login, new Name(name, lastName));
    }
}
