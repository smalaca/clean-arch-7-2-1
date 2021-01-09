package com.smalaca.rentalapplication.infrastructure.clock;

import com.smalaca.rentalapplication.domain.clock.Clock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class LocalClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
