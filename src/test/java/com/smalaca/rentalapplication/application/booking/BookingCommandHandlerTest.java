package com.smalaca.rentalapplication.application.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.booking.RentalPlaceIdentifier;
import com.smalaca.rentalapplication.domain.booking.RentalPlaceIdentifierTestFactory;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class BookingCommandHandlerTest {
    private static final String BOOKING_ID = "74398";
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID = "5678";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));

    private final ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);

    private final BookingRepository bookingRepository = mock(BookingRepository.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingCommandHandler commandHandler = new BookingCommandHandlerFactory().bookingCommandHandler(
            bookingRepository, new FakeEventIdFactory(), new FakeClock(), eventChannel);

    @Test
    void shouldAcceptBookingWhenBookingsWithCollisionNotFound() {
        givenBookingsWithoutCollision();
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));

        then(bookingRepository).should().save(captor.capture());
        BookingAssertion.assertThat(captor.getValue()).isAccepted();
    }

    @Test
    void shouldPublishBookingAcceptedOnceAccepted() {
        givenBookingsWithoutCollision();
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));

        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingAccepted actual = captor.getValue();
        Assertions.assertThat(actual.getRentalType()).isEqualTo("HOTEL_ROOM");
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    private void givenBookingsWithoutCollision() {
        RentalPlaceIdentifier identifier = RentalPlaceIdentifierTestFactory.hotelRoom(RENTAL_PLACE_ID);
        List<Booking> bookings = asList(
                Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS),
                Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS));

        given(bookingRepository.findAllBy(identifier)).willReturn(bookings);
    }

    @Test
    void shouldRejectBooking() {
        givenOpenBooking();

        commandHandler.reject(new BookingReject(BOOKING_ID));

        then(bookingRepository).should().save(captor.capture());
        BookingAssertion.assertThat(captor.getValue()).isRejected();
    }

    private void givenOpenBooking() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);
        given(bookingRepository.findById(BOOKING_ID)).willReturn(booking);
    }
}