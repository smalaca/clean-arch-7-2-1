package com.smalaca.rentalapplication.application.apartment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ApartmentBookingDto {
    private final String apartmentId;
    private final String tenantId;
    private final LocalDate start;
    private final LocalDate end;
}
