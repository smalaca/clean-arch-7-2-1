package com.smalaca.usermanagement.domain.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class User {
    private final String login;
    private final Name name;

    User(String login, Name name) {
        this.login = login;
        this.name = name;
    }
}
