package com.smalaca.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingAssertion;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.space.NotEnoughSpacesGivenException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;
import static com.smalaca.rentalapplication.domain.booking.NewBooking.forApartment;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

class ApartmentTest {
    private static final String OWNER_ID_1 = "1234";
    private static final String STREET_1 = "Florianska";
    private static final String POSTAL_CODE_1 = "12-345";
    private static final String HOUSE_NUMBER_1 = "1";
    private static final String APARTMENT_NUMBER_1 = "13";
    private static final String CITY_1 = "Cracow";
    private static final String COUNTRY_1 = "Poland";
    private static final String DESCRIPTION_1 = "Nice place to stay";
    private static final Map<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);
    private static final String OWNER_ID_2 = "4321";
    private static final String STREET_2 = "Grodzka";
    private static final String POSTAL_CODE_2 = "54-321";
    private static final String HOUSE_NUMBER_2 = "13";
    private static final String APARTMENT_NUMBER_2 = "42";
    private static final String CITY_2 = "Berlin";
    private static final String COUNTRY_2 = "Germany";
    private static final String DESCRIPTION_2 = "Lovely place";
    private static final Map<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("Toilet", 15.0, "RoomOne", 20.0, "RoomTwo", 25.0);
    private static final String TENANT_ID = "137";
    private static final LocalDate START = LocalDate.of(2040, 3, 4);
    private static final LocalDate END = LocalDate.of(2040, 3, 6);
    private static final Period PERIOD = Period.from(START, END);
    private static final String NO_ID = null;
    private static final List<Booking> NO_BOOKINGS = emptyList();
    private static final Money PRICE = Money.of(BigDecimal.valueOf(42));
    private static final String RENTAL_PLACE_ID = "123155";

    private final ApartmentEventsPublisher apartmentEventsPublisher = Mockito.mock(ApartmentEventsPublisher.class);

    @Test
    void shouldCreateApartmentWithAllRequiredFields() {
        Apartment actual = createApartment1();

        ApartmentAssertion.assertThat(actual)
                .isEqualTo(ApartmentRequirements.apartment()
                        .withOwnerId(OWNER_ID_1)
                        .withApartmentNumber(APARTMENT_NUMBER_1)
                        .withAddress(STREET_1, POSTAL_CODE_1, HOUSE_NUMBER_1, CITY_1, COUNTRY_1)
                )
                .hasDescriptionEqualsTo(DESCRIPTION_1)
                .hasSpacesEqualsTo(SPACES_DEFINITION_1);
    }

    @Test
    void shouldNotBeAbleToCreateApartmentWithoutSpaces() {
        Apartment.Builder apartment = apartment()
                .withOwnerId(OWNER_ID_1)
                .withStreet(STREET_1)
                .withPostalCode(POSTAL_CODE_1)
                .withHouseNumber(HOUSE_NUMBER_1)
                .withApartmentNumber(APARTMENT_NUMBER_1)
                .withCity(CITY_1)
                .withCountry(COUNTRY_1)
                .withDescription(DESCRIPTION_1);

        NotEnoughSpacesGivenException actual = assertThrows(NotEnoughSpacesGivenException.class, apartment::build);

        Assertions.assertThat(actual).hasMessage("No spaces given.");
    }

    @Test
    void shouldBookApartmentWhenNoBookingsGiven() {
        Apartment apartment = createApartment1();

        Booking actual = apartment.book(givenApartmentBooking(NO_BOOKINGS));

        BookingAssertion.assertThat(actual).isEqualToBookingApartment(NO_ID, TENANT_ID, OWNER_ID_1, PRICE, PERIOD);
        then(apartmentEventsPublisher).should().publishApartmentBooked(NO_ID, OWNER_ID_1, TENANT_ID, PERIOD);
    }

    @Test
    void shouldBookApartmentWhenBookingsNotForGivenPeriod() {
        Apartment apartment = createApartment1();
        Booking existingBooking = new Booking(forApartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID_1, PRICE, Period.from(START.minusDays(10), START.minusDays(5))));

        Booking actual = apartment.book(givenApartmentBooking(singletonList(existingBooking)));

        BookingAssertion.assertThat(actual).isEqualToBookingApartment(NO_ID, TENANT_ID, OWNER_ID_1, PRICE, PERIOD);
        then(apartmentEventsPublisher).should().publishApartmentBooked(NO_ID, OWNER_ID_1, TENANT_ID, PERIOD);
    }

    private ApartmentBooking givenApartmentBooking(List<Booking> bookings) {
        return new ApartmentBooking(bookings, TENANT_ID, PERIOD, PRICE, apartmentEventsPublisher);
    }

    @Test
    void shouldNotAllowForBookingWhenGivenBookingsForGivenPeriod() {
        Apartment apartment = createApartment1();
        Booking existingBooking = new Booking(forApartment(RENTAL_PLACE_ID, TENANT_ID, OWNER_ID_1, PRICE, Period.from(START, START.plusDays(5))));

        ApartmentBookingException actual = assertThrows(ApartmentBookingException.class, () -> apartment.book(givenApartmentBooking(singletonList(existingBooking))));

        assertThat(actual).hasMessage("There are accepted bookings in given period.");
        then(apartmentEventsPublisher).should(never()).publishApartmentBooked(any(), any(), any(), any());
    }

    @Test
    void shouldRecognizeTheSameInstanceAsTheSameAggregate() {
        Apartment actual = createApartment1();

        Assertions.assertThat(actual.equals(actual)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(actual.hashCode());
    }

    @Test
    void shouldRecognizeTwoInstancesOfApartmentRepresentsTheSameAggregate() {
        Apartment apartment2 = createApartment2SameAsApartment1().build();

        Apartment actual = createApartment1();

        Assertions.assertThat(actual.equals(apartment2)).isTrue();
        Assertions.assertThat(actual.hashCode()).isEqualTo(apartment2.hashCode());
    }

    @Test
    void shouldRecognizeNullIsNotTheSameAsApartment() {
        Apartment actual = createApartment1();

        Assertions.assertThat(actual.equals(null)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("notTheSameApartments")
    void shouldRecognizeApartmentDoesNotRepresentTheSameAggregate(Object notTheSame) {
        Apartment actual = createApartment1();

        Assertions.assertThat(actual.equals(notTheSame)).isFalse();
        Assertions.assertThat(actual.hashCode()).isNotEqualTo(notTheSame.hashCode());
    }

    private static Stream<Object> notTheSameApartments() {
        return Stream.of(
                createApartment2SameAsApartment1().withOwnerId(OWNER_ID_2).build(),
                createApartment2SameAsApartment1().withApartmentNumber(APARTMENT_NUMBER_2).build(),
                createApartment2SameAsApartment1().withStreet(STREET_2).build(),
                createApartment2SameAsApartment1().withPostalCode(POSTAL_CODE_2).build(),
                createApartment2SameAsApartment1().withHouseNumber(HOUSE_NUMBER_2).build(),
                createApartment2SameAsApartment1().withCity(CITY_2).build(),
                createApartment2SameAsApartment1().withCountry(COUNTRY_2).build(),
                new Object()
        );
    }

    private static Apartment.Builder createApartment2SameAsApartment1() {
        return apartment()
                .withOwnerId(OWNER_ID_1)
                .withStreet(STREET_1)
                .withPostalCode(POSTAL_CODE_1)
                .withHouseNumber(HOUSE_NUMBER_1)
                .withApartmentNumber(APARTMENT_NUMBER_1)
                .withCity(CITY_1)
                .withCountry(COUNTRY_1)
                .withDescription(DESCRIPTION_2)
                .withSpacesDefinition(SPACES_DEFINITION_2);
    }

    private Apartment createApartment1() {
        return apartment()
                .withOwnerId(OWNER_ID_1)
                .withStreet(STREET_1)
                .withPostalCode(POSTAL_CODE_1)
                .withHouseNumber(HOUSE_NUMBER_1)
                .withApartmentNumber(APARTMENT_NUMBER_1)
                .withCity(CITY_1)
                .withCountry(COUNTRY_1)
                .withDescription(DESCRIPTION_1)
                .withSpacesDefinition(SPACES_DEFINITION_1)
                .build();
    }
}