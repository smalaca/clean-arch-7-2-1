package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import java.time.LocalDate;
import java.util.List;

class HotelBookingDto {
    private final String tenantId;
    private final List<LocalDate> days;

    HotelBookingDto(String tenantId, List<LocalDate> days) {
        this.tenantId = tenantId;
        this.days = days;
    }

    String getTenantId() {
        return tenantId;
    }

    List<LocalDate> getDays() {
        return days;
    }
}
