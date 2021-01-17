package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

public final class HotelRoomOfferTestFactory {
    private HotelRoomOfferTestFactory() {}

    public static HotelRoomOffer create(String hotelId, int hotelRoomNumber, BigDecimal price, LocalDate start, LocalDate end) {
        return hotelRoomOffer()
                .withHotelId(hotelId)
                .withHotelRoomNumber(hotelRoomNumber)
                .withPrice(price)
                .withAvailability(start, end)
                .build();
    }
}