package com.smalaca.rentalapplication.domain.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingAcceptedTestFactory {
    public static BookingAccepted create(
            String eventId, LocalDateTime eventCreationDateTime, String rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
        return new BookingAccepted(eventId, eventCreationDateTime, rentalType, rentalPlaceId, tenantId, days);
    }
}