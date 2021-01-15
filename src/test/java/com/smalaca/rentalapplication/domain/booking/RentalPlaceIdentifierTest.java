package com.smalaca.rentalapplication.domain.booking;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class RentalPlaceIdentifierTest {
    @Test
    void shouldVerifyEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(RentalPlaceIdentifier.class).verify();
    }
}