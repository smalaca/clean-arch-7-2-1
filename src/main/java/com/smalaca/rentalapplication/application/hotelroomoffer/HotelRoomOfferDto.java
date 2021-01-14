package com.smalaca.rentalapplication.application.hotelroomoffer;

import com.smalaca.rentalapplication.domain.hotelroomoffer.CreateHotelRoomOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class HotelRoomOfferDto {
    private final String hotelId;
    private final int number;
    private final String hotelRoomId;
    private final BigDecimal price;
    private final LocalDate start;
    private final LocalDate end;

    CreateHotelRoomOffer asDto() {
        return new CreateHotelRoomOffer(number, hotelRoomId, price, start, end);
    }
}
