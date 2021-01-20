package com.smalaca.usermanagement.domain.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@SuppressWarnings("PMD.UnusedPrivateField")
class Name {
    private final String name;
    private final String lastName;

    Name(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
