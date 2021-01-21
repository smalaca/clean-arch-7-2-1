package com.smalaca.rentalapplication.domain.booking;

import com.smalaca.rentalapplication.domain.aggrement.Aggrement;
import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

class BookingDomainServiceTest {
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String OWNER_ID = "1982";
    private static final String TENANT_ID_1 = "5678";
    private static final String TENANT_ID_2 = "123456";
    public static final LocalDate TODAY = LocalDate.now();
    private static final List<LocalDate> DAYS = asList(TODAY, LocalDate.now().plusDays(1));
    private static final Period PERIOD = Period.from(TODAY, TODAY.plusDays(1));
    private static final Period PERIOD_WITH_COLLISION = Period.from(TODAY, TODAY.plusDays(10));
    private static final Period PERIOD_WITHOUT_COLLISION = Period.from(TODAY.plusDays(10), TODAY.plusDays(20));
    private static final List<Booking> NO_BOOKINGS_FOUND = emptyList();
    private static final Money PRICE = Money.of(BigDecimal.valueOf(42));

    private final EventIdFactory eventIdFactory = mock(EventIdFactory.class);
    private final Clock clock = mock(Clock.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingDomainService service = new BookingDomainServiceFactory().create(eventIdFactory, clock, eventChannel);
    private final BookingEventsPublisher bookingEventsPublisher = new BookingEventsPublisher(eventIdFactory, clock, eventChannel);

    @Test
    void shouldAcceptBookingWhenNoOtherBookingsFound() {
        Booking booking = givenApartmentBooking();

        Aggrement actual = service.accept(booking, emptyList()).get();

        BookingAssertion.assertThat(booking).isAccepted();
//        AgreementAssertion.assertThat(actual)
//                .isEqualToAgreement(APARTMENT, RENTAL_PLACE_ID, OWNER_ID, TENANT_ID, DAYS, PRICE);
    }

    @Test
    void shouldPublishBookingAcceptedEventWhenBookingIsAccepted() {
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);

        Aggrement actual = service.accept(givenApartmentBooking(), NO_BOOKINGS_FOUND).get();

        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingAccepted actualEvent = captor.getValue();
        Assertions.assertThat(actualEvent.getRentalType()).isEqualTo("APARTMENT");
        Assertions.assertThat(actualEvent.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actualEvent.getTenantId()).isEqualTo(TENANT_ID_1);
        Assertions.assertThat(actualEvent.getDays()).containsExactlyElementsOf(DAYS);
    }

    @Test
    void shouldRejectBookingWhenOtherWithDaysCollisionFound() {
        Booking booking = givenApartmentBooking();

        Optional<Aggrement> actual = service.accept(booking, asList(givenAcceptedBookingWithDaysCollision()));

        Assertions.assertThat(actual).isEmpty();
        BookingAssertion.assertThat(booking).isRejected();
    }

    @Test
    void shouldPublishBookingRejectedEventWhenBookingIsRejected() {
        ArgumentCaptor<BookingRejected> captor = ArgumentCaptor.forClass(BookingRejected.class);
        Booking booking = givenApartmentBooking();

        Optional<Aggrement> actual = service.accept(booking, asList(givenAcceptedBookingWithDaysCollision()));

        Assertions.assertThat(actual).isEmpty();
        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingRejected actualEvent = captor.getValue();
        Assertions.assertThat(actualEvent.getRentalType()).isEqualTo("APARTMENT");
        Assertions.assertThat(actualEvent.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actualEvent.getTenantId()).isEqualTo(TENANT_ID_1);
        Assertions.assertThat(actualEvent.getDays()).containsExactlyElementsOf(DAYS);
    }

    @Test
    void shouldAcceptBookingWhenOtherWithoutDaysCollisionFound() {
        Booking booking = givenApartmentBooking();

        Aggrement actual = service.accept(booking, asList(givenAcceptedBookingWithoutDaysCollision())).get();

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldAcceptBookingWhenOtherWithDaysCollisionButNotAcceptedFound() {
        Booking booking = givenApartmentBooking();

        Aggrement actual = service.accept(booking, asList(givenOpenBookingWithDaysCollision())).get();

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldAcceptBookingWhenFoundOnlyItself() {
        Booking booking = givenApartmentBooking();

        Aggrement actual = service.accept(booking, asList(booking)).get();

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldAcceptBookingWhenOthersWithoutCollisionFound() {
        Booking booking = givenApartmentBooking();
        List<Booking> bookings = asList(
                givenOpenBookingWithDaysCollision(), givenRejectedBookingWithDaysCollision(), givenAcceptedBookingWithoutDaysCollision());

        Aggrement actual = service.accept(booking, bookings).get();

        BookingAssertion.assertThat(booking).isAccepted();
    }

    @Test
    void shouldRejectBookingWhenAtLeastOneWithWithCollisionFound() {
        Booking booking = givenApartmentBooking();
        List<Booking> bookings = asList(
                givenOpenBookingWithDaysCollision(), givenRejectedBookingWithDaysCollision(),
                givenAcceptedBookingWithoutDaysCollision(), givenAcceptedBookingWithDaysCollision());

        Optional<Aggrement> actual = service.accept(booking, bookings);

        Assertions.assertThat(actual).isEmpty();
        BookingAssertion.assertThat(booking).isRejected();
    }

    private Booking givenRejectedBookingWithDaysCollision() {
        Booking booking = Booking.apartment(RENTAL_PLACE_ID, TENANT_ID_2, OWNER_ID, PRICE, PERIOD_WITH_COLLISION);
        booking.reject(bookingEventsPublisher);

        return booking;
    }

    private Booking givenAcceptedBookingWithoutDaysCollision() {
        Booking booking = Booking.apartment(RENTAL_PLACE_ID, TENANT_ID_2, OWNER_ID, PRICE, PERIOD_WITHOUT_COLLISION);
        booking.accept(bookingEventsPublisher);

        return booking;
    }

    private Booking givenAcceptedBookingWithDaysCollision() {
        Booking booking = Booking.apartment(RENTAL_PLACE_ID, TENANT_ID_2, OWNER_ID, PRICE, PERIOD_WITH_COLLISION);
        booking.accept(bookingEventsPublisher);
        return booking;
    }

    private Booking givenOpenBookingWithDaysCollision() {
        return Booking.apartment(RENTAL_PLACE_ID, TENANT_ID_2, OWNER_ID, PRICE, PERIOD_WITH_COLLISION);
    }

    private Booking givenApartmentBooking() {
        return Booking.apartment(RENTAL_PLACE_ID, TENANT_ID_1, OWNER_ID, PRICE, PERIOD);
    }
}