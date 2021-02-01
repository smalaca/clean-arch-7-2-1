package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.hotel.HotelEventsPublisher;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomBooking;
import com.smalaca.rentalapplication.domain.money.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class HotelRoomBookingDto {
    private final String hotelId;
    private final int number;
    private final String tenantId;
    private final BigDecimal price;
    private final List<LocalDate> days;

    HotelRoomBooking asHotelRoomBooking(HotelEventsPublisher hotelEventsPublisher) {
        return new HotelRoomBooking(number, tenantId, days, Money.of(price), hotelEventsPublisher);
    }
}
