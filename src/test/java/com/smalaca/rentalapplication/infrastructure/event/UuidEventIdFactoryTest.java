package com.smalaca.rentalapplication.infrastructure.event;

import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class UuidEventIdFactoryTest {
    private final EventIdFactory eventIdFactory = new UuidEventIdFactory();

    @Test
    void shouldReturnEventId() {
        String actual = eventIdFactory.create();

        assertThat(actual).matches(Pattern.compile("[0-9a-z\\-]{36}"));
    }
}