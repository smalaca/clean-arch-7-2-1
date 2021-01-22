package com.smalaca.rentalapplication.infrastructure.eventchannel.spring;

import com.smalaca.rentalapplication.domain.agreement.AgreementAccepted;
import com.smalaca.rentalapplication.domain.agreement.AgreementAcceptedTestFactory;
import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.apartment.ApartmentBookedTestFactory;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.booking.BookingAcceptedTestFactory;
import com.smalaca.rentalapplication.domain.booking.BookingRejected;
import com.smalaca.rentalapplication.domain.booking.BookingRejectedTestFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomBooked;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomBookedTestFactory;
import com.smalaca.rentalapplication.domain.period.Period;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class SpringEventChannelTest {
    private static final String EVENT_ID = UUID.randomUUID().toString();
    private static final LocalDateTime EVENT_CREATION_DATE_TIME = LocalDateTime.now();
    private static final String APARTMENT_ID = UUID.randomUUID().toString();
    private static final String OWNER_ID = UUID.randomUUID().toString();
    private static final String TENANT_ID = UUID.randomUUID().toString();
    private static final int HOTEL_ROOM_NUMBER = RandomUtils.nextInt();
    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final Period PERIOD = new Period(LocalDate.now(), LocalDate.now().plusDays(10));
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String RENTAL_TYPE = "HOTEL_ROOM";
    private static final String RENTAL_PLACE_ID = UUID.randomUUID().toString();
    private static final BigDecimal PRICE = BigDecimal.valueOf(42.13);

    private final ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
    private final EventChannel channel = new SpringEventChannel(applicationEventPublisher);

    @Test
    void shouldPublishApartmentBooked() {
        ApartmentBooked event = ApartmentBookedTestFactory.create(EVENT_ID, EVENT_CREATION_DATE_TIME, APARTMENT_ID, OWNER_ID, TENANT_ID, PERIOD);

        channel.publish(event);

        then(applicationEventPublisher).should().publishEvent(event);
    }

    @Test
    void shouldPublishHotelRoomBooked() {
        HotelRoomBooked event = HotelRoomBookedTestFactory.create(EVENT_ID, EVENT_CREATION_DATE_TIME, HOTEL_ID, HOTEL_ROOM_NUMBER, TENANT_ID, DAYS);

        channel.publish(event);

        then(applicationEventPublisher).should().publishEvent(event);
    }

    @Test
    void shouldPublishBookingAccepted() {
        BookingAccepted event = BookingAcceptedTestFactory.create(EVENT_ID, EVENT_CREATION_DATE_TIME, RENTAL_TYPE, RENTAL_PLACE_ID, TENANT_ID, DAYS);

        channel.publish(event);

        then(applicationEventPublisher).should().publishEvent(event);
    }

    @Test
    void shouldPublishBookingRejected() {
        BookingRejected event = BookingRejectedTestFactory.create(EVENT_ID, EVENT_CREATION_DATE_TIME, RENTAL_TYPE, RENTAL_PLACE_ID, TENANT_ID, DAYS);

        channel.publish(event);

        then(applicationEventPublisher).should().publishEvent(event);
    }

    @Test
    void shouldPublishAgreementAccepted() {
        AgreementAccepted event = AgreementAcceptedTestFactory.create(EVENT_ID, EVENT_CREATION_DATE_TIME, RENTAL_TYPE, RENTAL_PLACE_ID, OWNER_ID, TENANT_ID, DAYS, PRICE);

        channel.publish(event);

        then(applicationEventPublisher).should().publishEvent(event);
    }
}