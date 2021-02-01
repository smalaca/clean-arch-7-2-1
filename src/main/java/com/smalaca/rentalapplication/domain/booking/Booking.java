package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.agreement.Agreement;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.rentalplace.RentalPlaceIdentifier;
import com.smalaca.rentalapplication.domain.rentalplace.RentalType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.agreement.Agreement.Builder.agreement;
import static com.smalaca.rentalapplication.domain.booking.BookingStatus.ACCEPTED;
import static com.smalaca.rentalapplication.domain.booking.BookingStatus.REJECTED;

@Entity
@SuppressWarnings("PMD.UnusedPrivateField")
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RentalType rentalType;
    private String rentalPlaceId;
    private String tenantId;
    private String ownerId;
    @Embedded
    private Money price;
    @ElementCollection
    private List<LocalDate> days;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.OPEN;

    private Booking() {}

    private Booking(NewBooking newBooking) {
        this.rentalType = newBooking.getRentalType();
        this.rentalPlaceId = newBooking.getRentalPlaceId();
        this.tenantId = newBooking.getTenantId();
        this.ownerId = newBooking.getOwnerId();
        this.price = newBooking.getPrice();
        this.days = newBooking.getDays();
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    public static Booking apartment(String rentalPlaceId, String tenantId, String ownerId, Money price, Period period) {
        NewBooking newBooking = NewBooking.forApartment(rentalPlaceId, tenantId, ownerId, price, period);
        return new Booking(newBooking);
    }

    @Deprecated
    public static Booking apartment(String rentalPlaceId, String tenantId, Period period) {
        NewBooking newBooking = NewBooking.forApartment(rentalPlaceId, tenantId, "1234", Money.of(BigDecimal.valueOf(123)), period);
        return new Booking(newBooking);
    }

    @Deprecated
    public static Booking hotelRoom(String rentalPlaceId, String tenantId, List<LocalDate> days) {
        NewBooking newBooking = NewBooking.forHotelRoom(rentalPlaceId, tenantId, "2468", Money.of(BigDecimal.valueOf(135)), days);
        return new Booking(newBooking);
    }

    public void reject(BookingEventsPublisher bookingEventsPublisher) {
        bookingStatus = bookingStatus.moveTo(REJECTED);

        bookingEventsPublisher.bookingRejected(rentalType, rentalPlaceId, tenantId, days);
    }

    public Agreement accept(BookingEventsPublisher bookingEventsPublisher) {
        bookingStatus = bookingStatus.moveTo(ACCEPTED);

        bookingEventsPublisher.bookingAccepted(rentalType, rentalPlaceId, tenantId, days);
        return agreement()
                .withRentalType(rentalType)
                .withRentalPlaceId(rentalPlaceId)
                .withOwnerId(ownerId)
                .withTenantId(tenantId)
                .withDays(days)
                .withPrice(price)
                .build();
    }

    public String id() {
        return id.toString();
    }

    boolean hasCollisionWith(Booking booking) {
        return bookingStatus.equals(ACCEPTED) && hasDaysCollisionWith(booking);
    }

    private boolean hasDaysCollisionWith(Booking booking) {
        return days.stream().anyMatch(day -> booking.days.contains(day));
    }

    public RentalPlaceIdentifier rentalPlaceIdentifier() {
        return new RentalPlaceIdentifier(rentalType, rentalPlaceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Booking booking = (Booking) o;

        if (!days.containsAll(booking.days)) {
            return false;
        }

        return new EqualsBuilder()
                .append(rentalType, booking.rentalType)
                .append(rentalPlaceId, booking.rentalPlaceId)
                .append(tenantId, booking.tenantId)
                .append(ownerId, booking.ownerId)
                .append(price, booking.price)
                .isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(rentalType)
                .append(rentalPlaceId)
                .append(tenantId)
                .append(ownerId)
                .append(price)
                .append(days)
                .toHashCode();
    }

    public boolean isFor(Period period) {
        return days.stream().anyMatch(period::contains);
    }
}
