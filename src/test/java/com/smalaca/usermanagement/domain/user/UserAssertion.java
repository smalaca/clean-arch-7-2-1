package com.smalaca.usermanagement.domain.user;

import org.assertj.core.api.Assertions;

public class UserAssertion {
    private final User actual;

    private UserAssertion(User actual) {
        this.actual = actual;
    }

    public static UserAssertion assertThat(User actual) {
        return new UserAssertion(actual);
    }

    public void represents(String login, String name, String lastName) {
        User expected = new User(login, new Name(name, lastName));

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
