package com.smalaca.rentalapplication.infrastructure.event;

import com.smalaca.sharedkernel.domain.event.EventIdFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class UuidEventIdFactory implements EventIdFactory {
    @Override
    public String create() {
        return UUID.randomUUID().toString();
    }
}
