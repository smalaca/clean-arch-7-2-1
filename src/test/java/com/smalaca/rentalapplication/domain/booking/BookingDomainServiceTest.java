package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

class BookingDomainServiceTest {
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID_1 = "5678";
    private static final String TENANT_ID_2 = "123456";
    public static final LocalDate TODAY = LocalDate.now();
    private static final List<LocalDate> DAYS = asList(TODAY, LocalDate.now().plusDays(1));
    private static final List<LocalDate> DAYS_WITH_COLLISION = asList(TODAY, LocalDate.now().minusDays(13), LocalDate.now().plusDays(13));
    private static final List<LocalDate> DAYS_WITHOUT_COLLISION = asList(LocalDate.now().minusDays(13), LocalDate.now().plusDays(13));
    private static final List<Booking> NO_BOOKINGS_FOUND = emptyList();

    private final EventIdFactory eventIdFactory = mock(EventIdFactory.class);
    private final Clock clock = mock(Clock.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingDomainService service = new BookingDomainServiceFactory().create(eventIdFactory, clock, eventChannel);
    private final BookingEventsPublisher bookingEventsPublisher = new BookingEventsPublisher(eventIdFactory, clock, eventChannel);

    @Test
    void shouldAcceptBookingWhenNoOtherBookingsFound() {
        Booking booking = givenBooking();

        service.accept(booking, emptyList());

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldPublishBookingAcceptedEventWhenBookingIsAccepted() {
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);

        service.accept(givenBooking(), NO_BOOKINGS_FOUND);

        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingAccepted actual = captor.getValue();
        Assertions.assertThat(actual.getRentalType()).isEqualTo("HOTEL_ROOM");
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID_1);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    @Test
    void shouldRejectBookingWhenOtherWithDaysCollisionFound() {
        Booking booking = givenBooking();

        service.accept(booking, asList(givenAcceptedBookingWithDaysCollision()));

        BookingAssertion.assertThat(booking).isRejected();
    }

    @Test
    void shouldPublishBookingRejectedEventWhenBookingIsRejected() {
        ArgumentCaptor<BookingRejected> captor = ArgumentCaptor.forClass(BookingRejected.class);
        Booking booking = givenBooking();

        service.accept(booking, asList(givenAcceptedBookingWithDaysCollision()));

        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingRejected actual = captor.getValue();
        Assertions.assertThat(actual.getRentalType()).isEqualTo("HOTEL_ROOM");
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID_1);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    @Test
    void shouldAcceptBookingWhenOtherWithoutDaysCollisionFound() {
        Booking booking = givenBooking();

        service.accept(booking, asList(givenAcceptedBookingWithoutDaysCollision()));

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldAcceptBookingWhenOtherWithDaysCollisionButNotAcceptedFound() {
        Booking booking = givenBooking();

        service.accept(booking, asList(givenOpenBookingWithDaysCollision()));

        BookingAssertion.assertThat(booking).isAccepted();
    }

    private Booking givenAcceptedBookingWithoutDaysCollision() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID_2, DAYS_WITHOUT_COLLISION);
        booking.accept(bookingEventsPublisher);

        return booking;
    }

    private Booking givenAcceptedBookingWithDaysCollision() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID_2, DAYS_WITH_COLLISION);
        booking.accept(bookingEventsPublisher);
        return booking;
    }

    private Booking givenOpenBookingWithDaysCollision() {
        return Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID_2, DAYS_WITH_COLLISION);
    }

    private Booking givenBooking() {
        return Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID_1, DAYS);
    }
}