package com.smalaca.rentalapplication.domain.hotelroomoffer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class CreateHotelRoomOffer {
    private final String hotelId;
    private final int number;
    private final BigDecimal price;
    private final LocalDate start;
    private final LocalDate end;
}
