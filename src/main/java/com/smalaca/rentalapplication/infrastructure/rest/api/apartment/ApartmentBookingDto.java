package com.smalaca.rentalapplication.infrastructure.rest.api.apartment;

import java.time.LocalDate;

public class ApartmentBookingDto {
    private final String tenantId;
    private final LocalDate start;
    private final LocalDate end;

    public ApartmentBookingDto(String tenantId, LocalDate start, LocalDate end) {
        this.tenantId = tenantId;
        this.start = start;
        this.end = end;
    }

    public String getTenantId() {
        return tenantId;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
