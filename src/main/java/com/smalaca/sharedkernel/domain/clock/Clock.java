package com.smalaca.sharedkernel.domain.clock;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime now();
}
