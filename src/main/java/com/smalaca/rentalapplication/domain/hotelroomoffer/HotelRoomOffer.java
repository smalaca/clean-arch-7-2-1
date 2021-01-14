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
    private String hotelRoomId;
    @Embedded
    private Money money;
    @Embedded
    private HotelRoomAvailability availability;

    private HotelRoomOffer() {}

    private HotelRoomOffer(String hotelRoomId, Money money, HotelRoomAvailability availability) {
        this.hotelRoomId = hotelRoomId;
        this.money = money;
        this.availability = availability;
    }

    public UUID id() {
        return id;
    }

    static class Builder {
        private static final LocalDate NO_END_DATE = null;

        private String hotelRoomId;
        private BigDecimal price;
        private LocalDate start;
        private LocalDate end;

        private Builder() {}

        static Builder hotelRoomOffer() {
            return new Builder();
        }

        Builder withHotelRoomId(String hotelRoomId) {
            this.hotelRoomId = hotelRoomId;
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
            return new HotelRoomOffer(hotelRoomId, money(), hotelRoomAvailability());
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
