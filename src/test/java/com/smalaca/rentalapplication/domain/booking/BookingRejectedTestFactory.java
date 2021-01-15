package com.smalaca.rentalapplication.domain.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingRejectedTestFactory {
    public static BookingRejected create(
            String eventId, LocalDateTime eventCreationDateTime, String rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
        return new BookingRejected(eventId, eventCreationDateTime, rentalType, rentalPlaceId, tenantId, days);
    }
}