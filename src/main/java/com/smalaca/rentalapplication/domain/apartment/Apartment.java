package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.address.Address;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.rentalplace.RentalPlaceIdentifier;
import com.smalaca.rentalapplication.domain.space.Space;
import com.smalaca.rentalapplication.domain.space.SpacesFactory;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.rentalplace.RentalType.APARTMENT;

@Entity
@Table(name = "APARTMENT")
@SuppressWarnings({"PMD.UnusedPrivateField", "checkstyle:ClassFanOutComplexity"})
public class Apartment {
    @Id
    @GeneratedValue
    private UUID id;

    private String ownerId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "house_number"))
    })
    private Address address;

    private String apartmentNumber;

    @ElementCollection
    @CollectionTable(name = "APARTMENT_ROOM", joinColumns = @JoinColumn(name = "APARTMENT_ID"))
    @AttributeOverrides({
            @AttributeOverride(name = "squareMeter.value", column = @Column(name = "size"))
    })
    private List<Space> spaces;

    private String description;

    private Apartment() {}

    private Apartment(String ownerId, Address address, String apartmentNumber, List<Space> spaces, String description) {
        this.ownerId = ownerId;
        this.address = address;
        this.apartmentNumber = apartmentNumber;
        this.spaces = spaces;
        this.description = description;
    }

    Booking book(ApartmentBooking apartmentBooking) {
        Period period = apartmentBooking.getPeriod();
        String tenantId = apartmentBooking.getTenantId();

        if (areNotInGivenPeriod(apartmentBooking.getBookings(), period)) {
            apartmentBooking.getApartmentEventsPublisher().publishApartmentBooked(id(), ownerId, tenantId, period);

            return Booking.apartment(id(), tenantId, ownerId, apartmentBooking.getPrice(), period);
        } else {
            throw new ApartmentBookingException();
        }
    }

    @Deprecated
    @SuppressWarnings("checkstyle:MagicNumber")
    Booking book(List<Booking> bookings, String tenantId, Period period, ApartmentEventsPublisher apartmentEventsPublisher) {
        apartmentEventsPublisher.publishApartmentBooked(id(), ownerId, tenantId, period);

        return Booking.apartment(id(), tenantId, ownerId, Money.of(BigDecimal.valueOf(42)), period);
    }

    private boolean areNotInGivenPeriod(List<Booking> bookings, Period period) {
        return bookings.stream().noneMatch(booking -> booking.isFor(period));
    }

    RentalPlaceIdentifier rentalPlaceIdentifier() {
        return new RentalPlaceIdentifier(APARTMENT, id());
    }

    public String id() {
        if (id == null) {
            return null;
        }

        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Apartment apartment = (Apartment) o;

        return new EqualsBuilder()
                .append(ownerId, apartment.ownerId)
                .append(address, apartment.address)
                .append(apartmentNumber, apartment.apartmentNumber)
                .isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(ownerId)
                .append(address)
                .append(apartmentNumber)
                .toHashCode();
    }

    static class Builder {
        private String ownerId;
        private String street;
        private String postalCode;
        private String houseNumber;
        private String apartmentNumber;
        private String city;
        private String country;
        private String description;
        private Map<String, Double> spacesDefinition = new HashMap<>();

        private Builder() {}

        static Builder apartment() {
            return new Builder();
        }

        Builder withOwnerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        Builder withApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        Builder withCity(String city) {
            this.city = city;
            return this;
        }

        Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        Builder withSpacesDefinition(Map<String, Double> spacesDefinition) {
            this.spacesDefinition = spacesDefinition;
            return this;
        }

        Apartment build() {
            return new Apartment(ownerId, address(), apartmentNumber, spaces(), description);
        }

        private Address address() {
            return new Address(street, postalCode, houseNumber, city, country);
        }

        private List<Space> spaces() {
            return SpacesFactory.create(spacesDefinition);
        }
    }
}
