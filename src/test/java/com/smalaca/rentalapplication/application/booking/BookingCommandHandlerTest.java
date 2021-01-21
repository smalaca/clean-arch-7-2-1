package com.smalaca.rentalapplication.application.booking;

import com.smalaca.rentalapplication.domain.aggrement.Agreement;
import com.smalaca.rentalapplication.domain.aggrement.AgreementAssertion;
import com.smalaca.rentalapplication.domain.aggrement.AgreementRepository;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import com.smalaca.rentalapplication.domain.booking.BookingEventsPublisher;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.booking.RentalPlaceIdentifier;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.smalaca.rentalapplication.domain.booking.RentalType.APARTMENT;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class BookingCommandHandlerTest {
    private static final String BOOKING_ID = "74398";
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID = "5678";
    private static final String OWNER_ID = "1982";
    private static final Money PRICE = Money.of(BigDecimal.valueOf(42));
    public static final LocalDate TODAY = LocalDate.now();
    private static final Period PERIOD = Period.from(TODAY, TODAY.plusDays(1));
    private static final List<LocalDate> DAYS = asList(TODAY, TODAY.plusDays(1));

    private final ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);

    private final BookingRepository bookingRepository = mock(BookingRepository.class);
    private final AgreementRepository agreementRepository = mock(AgreementRepository.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final FakeEventIdFactory eventIdFactory = new FakeEventIdFactory();
    private final FakeClock clock = new FakeClock();
    private final BookingCommandHandler commandHandler = new BookingCommandHandlerFactory().bookingCommandHandler(
            bookingRepository, agreementRepository, eventIdFactory, clock, eventChannel);

    @Test
    void shouldCreateAgreementWhenBookingAccepted() {
        ArgumentCaptor<Agreement> captor = ArgumentCaptor.forClass(Agreement.class);
        givenBookingsWithoutCollision();
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));

        then(agreementRepository).should().save(captor.capture());
        AgreementAssertion.assertThat(captor.getValue())
                .isEqualToAgreement(APARTMENT, RENTAL_PLACE_ID, OWNER_ID, TENANT_ID, DAYS, PRICE);
    }

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
        Assertions.assertThat(actual.getRentalType()).isEqualTo("APARTMENT");
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    private void givenBookingsWithoutCollision() {
        givenBookings(asList(
                Booking.apartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID, PRICE, PERIOD),
                Booking.apartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID, PRICE, PERIOD)));
    }

    @Test
    void shouldRejectBookingWhenBookingsWithCollisionFound() {
        givenBookingsWithCollision();
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));

        then(bookingRepository).should().save(captor.capture());
        BookingAssertion.assertThat(captor.getValue()).isRejected();
    }

    @Test
    void shouldNotCreateAgreementWhenBookingRejectedDuringAcceptance() {
        givenBookingsWithCollision();
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));

        then(agreementRepository).should(never()).save(any());
    }


    private void givenBookingsWithCollision() {
        Booking booking = Booking.apartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID, PRICE, PERIOD);
        booking.accept(new BookingEventsPublisher(eventIdFactory, clock, eventChannel));
        givenBookings(asList(booking, Booking.apartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID, PRICE, PERIOD)));
    }

    private void givenBookings(List<Booking> bookings) {
        RentalPlaceIdentifier identifier = new RentalPlaceIdentifier(APARTMENT, RENTAL_PLACE_ID);
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
        Booking booking = Booking.apartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID, PRICE, PERIOD);
        given(bookingRepository.findById(BOOKING_ID)).willReturn(booking);
    }
}