package com.smalaca.rentalapplication.domain.address;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    void shouldVerifyEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Address.class).verify();
    }
}