package com.smalaca.rentalapplication.domain.hotel;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.space.Space;
import com.smalaca.rentalapplication.domain.space.SpacesFactory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "HOTEL_ROOM")
@SuppressWarnings("PMD.UnusedPrivateField")
public class HotelRoom {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "HOTEL_ID")
    private UUID hotelId;
    private int number;

    @ElementCollection
    @CollectionTable(name = "HOTEL_ROOM_SPACE", joinColumns = @JoinColumn(name = "HOTEL_ROOM_ID"))
    private List<Space> spaces;

    private String description;

    private HotelRoom() {}

    private HotelRoom(UUID hotelId, int number, List<Space> spaces, String description) {
        this.hotelId = hotelId;
        this.number = number;
        this.spaces = spaces;
        this.description = description;
    }

    Booking book(String tenantId, List<LocalDate> days, HotelEventsPublisher hotelEventsPublisher) {
        hotelEventsPublisher.publishHotelRoomBooked(hotelId(), number, tenantId, days);

        return Booking.hotelRoom(id(), tenantId, days);
    }

    private String hotelId() {
        return getNullable(hotelId);
    }

    private String id() {
        return getNullable(id);
    }

    private String getNullable(UUID id) {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    boolean hasNumberEqualTo(int number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HotelRoom hotelRoom = (HotelRoom) o;

        return new EqualsBuilder().append(number, hotelRoom.number).append(hotelId, hotelRoom.hotelId).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(hotelId).append(number).toHashCode();
    }

    public static class Builder {
        private UUID hotelId;
        private int number;
        private Map<String, Double> spacesDefinition = new HashMap<>();
        private String description;

        private Builder() {}

        public static Builder hotelRoom() {
            return new Builder();
        }

        Builder withHotelId(UUID hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        public Builder withHotelId(String hotelId) {
            return withHotelId(UUID.fromString(hotelId));
        }

        public Builder withNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder withSpacesDefinition(Map<String, Double> spacesDefinition) {
            this.spacesDefinition = spacesDefinition;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public HotelRoom build() {
            return new HotelRoom(hotelId, number, spaces(), description);
        }

        private List<Space> spaces() {
            return SpacesFactory.create(spacesDefinition);
        }
    }
}
