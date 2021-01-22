package com.smalaca.usermanagement.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class UserTest {
    private static final String LOGIN = "spiderman";
    private static final String NAME = "Peter";
    private static final String LAST_NAME = "Parker";

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        User actual = createUser();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfUserRepresentsTheSameAggregate() {
        User actual = createUser();

        Assertions.assertThat(actual.equals(createUser())).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(createUser().hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsUser() {
        User actual = createUser();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTheSameUsers")
    void shouldRecognizeUserDoesNotRepresentTheSameAggregate(Object notTheSame) {
        User actual = createUser();

        Assertions.assertThat(actual.equals(notTheSame)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(notTheSame.hashCode());
    }

    private static Stream<Object> notTheSameUsers() {
        return Stream.of(
                new User("LOGIN", new Name(NAME, LAST_NAME)),
                new User(LOGIN, new Name("NAME", LAST_NAME)),
                new User(LOGIN, new Name(NAME, "LAST_NAME")),
                new Object()
        );
    }

    private User createUser() {
        return new User(LOGIN, new Name(NAME, LAST_NAME));
    }
}