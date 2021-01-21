package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
class ApartmentBooking {
    private final List<Booking> bookings;
    private final String tenantId;
    private final Period period;
    private final Money price;
    private final ApartmentEventsPublisher apartmentEventsPublisher;
}
