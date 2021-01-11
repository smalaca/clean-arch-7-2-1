package com.smalaca.rentalapplication.application.hotelroomoffer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class HotelRoomOfferDto {
    private final String hotelRoomId;
    private final BigDecimal price;
    private final LocalDate start;
    private final LocalDate end;
}
