package com.smalaca.rentalapplication.domain.hotel;

import com.smalaca.rentalapplication.domain.money.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter(value = AccessLevel.PACKAGE)
@AllArgsConstructor
public class HotelRoomBooking {
    private final int number;
    private final String tenantId;
    private final List<LocalDate> days;
    private final Money price;
    private final HotelEventsPublisher hotelEventsPublisher;
}
