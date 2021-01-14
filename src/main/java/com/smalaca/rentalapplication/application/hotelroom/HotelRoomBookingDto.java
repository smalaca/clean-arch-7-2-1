package com.smalaca.rentalapplication.application.hotelroom;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class HotelRoomBookingDto {
    private final String hotelId;
    private final int number;
    private final String hotelRoomId;
    private final String tenantId;
    private final List<LocalDate> days;
}
