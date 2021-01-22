package com.smalaca.usermanagement.domain.user;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@SuppressWarnings("PMD.UnusedPrivateField")
class Name {
    private String name;
    private String lastName;

    private Name() {}

    Name(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
