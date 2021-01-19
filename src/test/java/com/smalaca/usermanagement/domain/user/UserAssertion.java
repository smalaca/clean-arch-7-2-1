package com.smalaca.usermanagement.domain.user;

import org.assertj.core.api.Assertions;

import static com.smalaca.usermanagement.domain.user.User.Builder.user;

public class UserAssertion {
    private final User actual;

    private UserAssertion(User actual) {
        this.actual = actual;
    }

    public static UserAssertion assertThat(User actual) {
        return new UserAssertion(actual);
    }

    public void represents(String login, String name, String lastName) {
        User expected = user().withLogin(login).withName(name, lastName).build();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
