package com.smalaca.usermanagement.domain.user;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void shouldVerifyEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(User.class).verify();
    }
}