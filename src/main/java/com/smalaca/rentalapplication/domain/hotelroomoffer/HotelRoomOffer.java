package com.smalaca.rentalapplication.domain.hotelroomoffer;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class HotelRoomOffer {
    @Id
    @GeneratedValue
    private UUID id;

    private String hotelId;

    private int hotelRoomNumber;

    @Embedded
    private Money money;
    @Embedded
    private HotelRoomAvailability availability;

    private HotelRoomOffer() {}

    private HotelRoomOffer(String hotelId, int hotelRoomNumber, Money money, HotelRoomAvailability availability) {
        this.hotelId = hotelId;
        this.hotelRoomNumber = hotelRoomNumber;
        this.money = money;
        this.availability = availability;
    }

    public UUID id() {
        return id;
    }

    static class Builder {
        private static final LocalDate NO_END_DATE = null;

        private String hotelId;
        private int hotelRoomNumber;
        private BigDecimal price;
        private LocalDate start;
        private LocalDate end;

        private Builder() {}

        static Builder hotelRoomOffer() {
            return new Builder();
        }

        Builder withHotelId(String hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        Builder withHotelRoomNumber(int hotelRoomNumber) {
            this.hotelRoomNumber = hotelRoomNumber;
            return this;
        }

        Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        Builder withAvailability(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
            return this;
        }

        HotelRoomOffer build() {
            return new HotelRoomOffer(hotelId, hotelRoomNumber, money(), hotelRoomAvailability());
        }

        private HotelRoomAvailability hotelRoomAvailability() {
            if (end == NO_END_DATE) {
                return HotelRoomAvailability.fromStart(start);
            }

            return HotelRoomAvailability.from(start, end);
        }

        private Money money() {
            return Money.of(price);
        }
    }
}
