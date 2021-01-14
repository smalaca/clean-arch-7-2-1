package com.smalaca.rentalapplication.domain.hotelroomoffer;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.hotelroomoffer.HotelRoomOffer.Builder.hotelRoomOffer;

public final class HotelRoomOfferTestFactory {
    private HotelRoomOfferTestFactory() {}

    public static HotelRoomOffer create(String hotelRoomId, BigDecimal price, LocalDate start, LocalDate end) {
        return hotelRoomOffer()
                .withHotelRoomId(hotelRoomId)
                .withPrice(price)
                .withAvailability(start, end)
                .build();
    }
}