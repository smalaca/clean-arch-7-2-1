package com.smalaca.usermanagement.domain.user;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    void shouldVerifyEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Name.class).verify();
    }
}