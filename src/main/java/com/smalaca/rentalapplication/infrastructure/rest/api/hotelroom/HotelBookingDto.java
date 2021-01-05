package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import java.time.LocalDate;
import java.util.List;

public class HotelBookingDto {
    private final String tenantId;
    private final List<LocalDate> days;

    public HotelBookingDto(String tenantId, List<LocalDate> days) {
        this.tenantId = tenantId;
        this.days = days;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<LocalDate> getDays() {
        return days;
    }
}
