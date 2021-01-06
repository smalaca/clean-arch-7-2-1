package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelFactory;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.smalaca.rentalapplication.domain.hotel.HotelAssertion.assertThat;

@SpringBootTest
@Tag("IntegrationTest")
class JpaHotelRepositoryIntegrationTest {
    private static final String NAME = "Great hotel";
    private static final String STREET = "Unknown";
    private static final String POSTAL_CODE = "12-345";
    private static final String BUILDING_NUMBER = "13";
    private static final String CITY = "Somewhere";
    private static final String COUNTRY = "Nowhere";

    @Autowired private HotelRepository hotelRepository;
    @Autowired private SpringJpaHotelRepository springJpaHotelRepository;

    private String hotelId;

    @AfterEach
    void deleteHotel() {
        springJpaHotelRepository.deleteById(UUID.fromString(hotelId));
    }

    @Test
    void shouldSaveHotel() {
        Hotel hotel = new HotelFactory().create(NAME, STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);

        hotelId = hotelRepository.save(hotel);

        assertThat(findBy(hotelId))
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);
    }

    private Hotel findBy(String hotelId) {
        return springJpaHotelRepository.findById(UUID.fromString(hotelId)).get();
    }
}