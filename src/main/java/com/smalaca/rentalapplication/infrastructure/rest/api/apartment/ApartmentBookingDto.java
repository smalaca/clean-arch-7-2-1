package com.smalaca.rentalapplication.infrastructure.rest.api.apartment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ApartmentBookingDto {
    private final String tenantId;
    private final LocalDate start;
    private final LocalDate end;
}
