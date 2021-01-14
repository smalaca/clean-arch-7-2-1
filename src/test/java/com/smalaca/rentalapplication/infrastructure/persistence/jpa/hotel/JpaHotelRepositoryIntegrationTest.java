package com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomAssertion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static com.smalaca.rentalapplication.domain.hotel.HotelAssertion.assertThat;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaHotelRepositoryIntegrationTest {
    private static final String NAME = "Great hotel";
    private static final String STREET = "Unknown";
    private static final String POSTAL_CODE = "12-345";
    private static final String BUILDING_NUMBER = "13";
    private static final String CITY = "Somewhere";
    private static final String COUNTRY = "Nowhere";
    private static final int ROOM_NUMBER_1 = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION_1 = "This is very nice place";
    private static final int ROOM_NUMBER_2 = 13;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("RoomOne", 10.0, "RoomTwo", 25.0);
    private static final String DESCRIPTION_2 = "This is even better place";

    @Autowired private HotelRepository hotelRepository;
    @Autowired private SpringJpaHotelRepository springJpaHotelRepository;

    private String hotelId;

    @AfterEach
    void deleteHotel() {
        springJpaHotelRepository.deleteById(UUID.fromString(hotelId));
    }

    @Test
    void shouldSaveHotel() {
        hotelId = hotelRepository.save(givenHotel());

        assertThat(findBy(hotelId))
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY);
    }

    @Test
    @Transactional
    void shouldCreateHotelWithRooms() {
        Hotel hotel = givenExistingHotel();
        hotel.addRoom(ROOM_NUMBER_1, SPACES_DEFINITION_1, DESCRIPTION_1);
        hotel.addRoom(ROOM_NUMBER_2, SPACES_DEFINITION_2, DESCRIPTION_2);

        hotelRepository.save(hotel);

        assertThat(findBy(hotelId))
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, BUILDING_NUMBER, CITY, COUNTRY)
                .hasHotelRooms(2)
                .hasHotelRoom(hotelRoom -> {
                    HotelRoomAssertion.assertThat(hotelRoom)
                            .hasRoomNumberEqualTo(ROOM_NUMBER_1)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_1)
                            .hasDescriptionEqualTo(DESCRIPTION_1);
                })
                .hasHotelRoom(hotelRoom -> {
                    HotelRoomAssertion.assertThat(hotelRoom)
                            .hasRoomNumberEqualTo(ROOM_NUMBER_2)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_2)
                            .hasDescriptionEqualTo(DESCRIPTION_2);
                })
        ;
    }

    private Hotel givenExistingHotel() {
        Hotel hotel = givenHotel();
        hotelId = hotelRepository.save(hotel);

        return hotel;
    }

    private Hotel givenHotel() {
        return hotel()
                .withName(NAME)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withBuildingNumber(BUILDING_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();
    }

    private Hotel findBy(String hotelId) {
        return springJpaHotelRepository.findById(UUID.fromString(hotelId)).get();
    }
}