package com.smalaca.rentalapplication.application.apartment;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentAssertion;
import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.apartment.ApartmentNotFoundException;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRequirements;
import com.smalaca.rentalapplication.domain.apartment.OwnerDoesNotExistException;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAccepted;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.event.FakeEventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.owner.OwnerRepository;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.space.SquareMeterException;
import com.smalaca.rentalapplication.domain.tenant.TenantNotFoundException;
import com.smalaca.rentalapplication.domain.tenant.TenantRepository;
import com.smalaca.rentalapplication.infrastructure.clock.FakeClock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Map;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class ApartmentApplicationServiceTest {
    private static final String APARTMENT_ID = "2178231";
    private static final String OWNER_ID = "1234";
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String APARTMENT_NUMBER = "13";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";
    private static final String DESCRIPTION = "Nice place to stay";
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);
    private static final String TENANT_ID = "137";
    private static final LocalDate START = LocalDate.of(2020, 3, 4);
    private static final LocalDate END = LocalDate.of(2020, 3, 6);
    private static final String BOOKING_ID = "8394234";
    private static final String NO_ID = null;

    private final OwnerRepository ownerRepository = mock(OwnerRepository.class);
    private final TenantRepository tenantRepository = mock(TenantRepository.class);
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    private final BookingRepository bookingRepository = mock(BookingRepository.class);
    private final ApartmentApplicationService service = new ApartmentApplicationServiceFactory()
            .apartmentApplicationService(apartmentRepository, bookingRepository, ownerRepository, tenantRepository, new FakeEventIdFactory(), new FakeClock(), eventChannel);

    @Test
    void shouldAddNewApartment() {
        givenOwnerExists();
        ArgumentCaptor<Apartment> captor = ArgumentCaptor.forClass(Apartment.class);

        service.add(givenApartmentDto());

        then(apartmentRepository).should().save(captor.capture());
        ApartmentAssertion.assertThat(captor.getValue())
                .isEqualTo(ApartmentRequirements.apartment()
                        .withOwnerId(OWNER_ID)
                        .withApartmentNumber(APARTMENT_NUMBER)
                        .withAddress(STREET, POSTAL_CODE, HOUSE_NUMBER, CITY, COUNTRY)
                )
                .hasDescriptionEqualsTo(DESCRIPTION)
                .hasSpacesEqualsTo(SPACES_DEFINITION);
    }

    @Test
    void shouldReturnIdOfNewApartment() {
        givenOwnerExists();
        given(apartmentRepository.save(any())).willReturn(APARTMENT_ID);

        String actual = service.add(givenApartmentDto());

        Assertions.assertThat(actual).isEqualTo(APARTMENT_ID);
    }

    private ApartmentDto givenApartmentDto() {
        return givenApartmentDtoWith(SPACES_DEFINITION);
    }

    @Test
    void shouldNotAllowToCreateApartmentWithAtLeastOneSpaceThatHaveSquareMeterEqualZero() {
        givenOwnerExists();
        ApartmentDto apartmentDto = givenApartmentDtoWith(ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0, "Room", 0.0));

        SquareMeterException actual = assertThrows(SquareMeterException.class, () -> service.add(apartmentDto));

        assertThat(actual).hasMessage("Square meter cannot be lower or equal zero.");
        then(apartmentRepository).should(never()).save(any());
    }

    @Test
    void shouldNotAllowToCreateApartmentWithAtLeastOneSpaceThatHaveSquareMeterLowerThanZero() {
        givenOwnerExists();
        ApartmentDto apartmentDto = givenApartmentDtoWith(ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0, "Room", -13.0));

        SquareMeterException actual = assertThrows(SquareMeterException.class, () -> service.add(apartmentDto));

        assertThat(actual).hasMessage("Square meter cannot be lower or equal zero.");
        then(apartmentRepository).should(never()).save(any());
    }

    private ApartmentDto givenApartmentDtoWith(Map<String, Double> spacesDefinition) {
        return new ApartmentDto(OWNER_ID, STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY, DESCRIPTION, spacesDefinition);
    }

    private void givenOwnerExists() {
        given(ownerRepository.exists(OWNER_ID)).willReturn(true);
    }

    @Test
    void shouldRecognizeOwnerDoesNotExist() {
        givenOwnerDoesNotExist();

        OwnerDoesNotExistException actual = assertThrows(OwnerDoesNotExistException.class, () -> service.add(givenApartmentDto()));

        assertThat(actual).hasMessage("Owner with id " + OWNER_ID + " does not exist.");
        then(apartmentRepository).should(never()).save(any());
    }

    private void givenOwnerDoesNotExist() {
        given(ownerRepository.exists(OWNER_ID)).willReturn(false);
    }

    @Test
    void shouldCreateBookingForApartment() {
        givenExistingTenantAndApartment();
        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);

        service.book(givenBookApartmentDto());

        then(bookingRepository).should().save(captor.capture());
        BookingAssertion.assertThat(captor.getValue())
                .isEqualToBookingApartment(NO_ID, TENANT_ID, new Period(START, END));
    }

    @Test
    void shouldReturnIdOfBooking() {
        givenExistingTenantAndApartment();
        given(bookingRepository.save(any())).willReturn(BOOKING_ID);

        String actual = service.book(givenBookApartmentDto());

        Assertions.assertThat(actual).isEqualTo(BOOKING_ID);
    }

    @Test
    void shouldPublishApartmentBookedEvent() {
        givenExistingTenantAndApartment();
        ArgumentCaptor<ApartmentBooked> captor = ArgumentCaptor.forClass(ApartmentBooked.class);

        service.book(givenBookApartmentDto());

        then(eventChannel).should().publish(captor.capture());
        ApartmentBooked actual = captor.getValue();
        assertThat(actual.getEventId()).isEqualTo(FakeEventIdFactory.UUID);
        assertThat(actual.getEventCreationDateTime()).isEqualTo(FakeClock.NOW);
        assertThat(actual.getOwnerId()).isEqualTo(OWNER_ID);
        assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        assertThat(actual.getPeriodStart()).isEqualTo(START);
        assertThat(actual.getPeriodEnd()).isEqualTo(END);
    }

    private void givenExistingTenantAndApartment() {
        givenExistingApartment();
        givenExistingTenant();
    }

    @Test
    void shouldRecognizeApartmentDoesNotExistWhenBooking() {
        givenNonExistingApartment();
        givenExistingTenant();

        ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> service.book(givenBookApartmentDto()));

        assertThat(actual).hasMessage("Apartment with id: " + APARTMENT_ID + " does not exist.");
        thenBookingWasNotCreated();
    }

    private void givenNonExistingApartment() {
        given(apartmentRepository.existById(APARTMENT_ID)).willReturn(false);
    }

    @Test
    void shouldRecognizeTenantDoesNotExistWhenBooking() {
        givenExistingApartment();
        givenNonExistingTenant();

        TenantNotFoundException actual = assertThrows(TenantNotFoundException.class, () -> service.book(givenBookApartmentDto()));

        assertThat(actual).hasMessage("Tenant with id: " + TENANT_ID + " does not exist.");
        thenBookingWasNotCreated();
    }

    private void givenNonExistingTenant() {
        given(tenantRepository.existById(TENANT_ID)).willReturn(false);
    }

    private void thenBookingWasNotCreated() {
        then(bookingRepository).should(never()).save(any());
        then(eventChannel).should(never()).publish(any(BookingAccepted.class));
    }

    private ApartmentBookingDto givenBookApartmentDto() {
        return new ApartmentBookingDto(APARTMENT_ID, TENANT_ID, START, END);
    }

    private void givenExistingTenant() {
        given(tenantRepository.existById(TENANT_ID)).willReturn(true);
    }

    private void givenExistingApartment() {
        Apartment apartment = apartment()
                .withOwnerId(OWNER_ID)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withHouseNumber(HOUSE_NUMBER)
                .withApartmentNumber(APARTMENT_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .withDescription(DESCRIPTION)
                .withSpacesDefinition(SPACES_DEFINITION)
                .build();

        given(apartmentRepository.existById(APARTMENT_ID)).willReturn(true);
        given(apartmentRepository.findById(APARTMENT_ID)).willReturn(apartment);
    }
}