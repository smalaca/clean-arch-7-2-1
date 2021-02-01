package com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.rentalplace.RentalPlaceIdentifier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.smalaca.rentalapplication.domain.booking.BookingAssertion.assertThat;
import static com.smalaca.rentalapplication.domain.booking.NewBooking.forApartment;
import static com.smalaca.rentalapplication.domain.booking.NewBooking.forHotelRoom;
import static com.smalaca.rentalapplication.domain.rentalplace.RentalType.APARTMENT;
import static java.util.Arrays.asList;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaBookingRepositoryIntegrationTest {
    private static final List<LocalDate> DAYS = asList(LocalDate.of(2020, 6, 1), LocalDate.of(2020, 6, 2), LocalDate.of(2020, 6, 4));
    private static final String TENANT_ID = randomId();
    private static final Period PERIOD = Period.from(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String OWNER_ID = randomId();
    private static final Money PRICE = Money.of(BigDecimal.valueOf(246));

    @Autowired private JpaBookingRepository repository;
    @Autowired private SpringJpaBookingRepository jpaRepository;
    private final List<String> bookingIds = new ArrayList<>();

    @AfterEach
    void deleteBooking() {
        bookingIds.forEach(id -> jpaRepository.deleteById(UUID.fromString(id)));
    }

    @Test
    @Transactional
    void shouldFindExistingBooking() {
        String rentalPlaceId = randomId();
        Booking booking = new Booking(forHotelRoom(rentalPlaceId, TENANT_ID, OWNER_ID, PRICE, DAYS));
        String bookingId = save(booking);

        Booking actual = repository.findById(bookingId);

        assertThat(actual)
                .isOpen()
                .isEqualToBookingHotelRoom(rentalPlaceId, TENANT_ID, OWNER_ID, PRICE, DAYS);
    }

    @Test
    void shouldFindBookingsByRentalPlaceIdentifier() {
        String rentalPlaceId1 = randomId();
        String rentalPlaceId2 = randomId();
        Booking booking = new Booking(forHotelRoom(rentalPlaceId1, TENANT_ID, OWNER_ID, PRICE, DAYS));
        String bookingId = save(booking);
        String bookingId1 = save(new Booking(forHotelRoom(rentalPlaceId1, TENANT_ID, OWNER_ID, PRICE, DAYS)));
        String bookingId2 = save(new Booking(forHotelRoom(rentalPlaceId1, TENANT_ID, OWNER_ID, PRICE, DAYS)));
        save(new Booking(forHotelRoom(rentalPlaceId2, TENANT_ID, OWNER_ID, PRICE, DAYS)));
        save(new Booking(forApartment(rentalPlaceId2, TENANT_ID, OWNER_ID, PRICE, PERIOD)));
        save(new Booking(forApartment(rentalPlaceId1, TENANT_ID, OWNER_ID, PRICE, PERIOD)));

        List<Booking> actual = repository.findAllBy(booking.rentalPlaceIdentifier());

        Assertions.assertThat(actual)
                .hasSize(3)
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId))
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId1))
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId2));
    }

    @Test
    void shouldFindNoAcceptedBookingsByRentalPlaceIdentifier() {
        repository.findAllAcceptedBy(new RentalPlaceIdentifier(APARTMENT, randomId()));
    }

    private String save(Booking booking) {
        String bookingId = repository.save(booking);
        bookingIds.add(bookingId);

        return bookingId;
    }

    private static String randomId() {
        return UUID.randomUUID().toString();
    }
}