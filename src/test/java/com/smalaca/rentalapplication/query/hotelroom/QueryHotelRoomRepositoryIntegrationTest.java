package com.smalaca.rentalapplication.query.hotelroom;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.domain.hotel.HotelRoom;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomRepository;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroom.SpringJpaHotelRoomTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static com.smalaca.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Tag("IntegrationTest")
class QueryHotelRoomRepositoryIntegrationTest {
    private static final String HOTEL_ID = "5678";
    private static final int ROOM_NUMBER_1 = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION_1 = "This is very nice place";
    private static final int ROOM_NUMBER_2 = 13;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("RoomOne", 10.0, "RoomTwo", 25.0);
    private static final String DESCRIPTION_2 = "This is even better place";

    @Autowired private HotelRoomRepository hotelRoomRepository;
    @Autowired private QueryHotelRoomRepository queryHotelRoomRepository;
    @Autowired private SpringJpaHotelRoomTestRepository springJpaHotelRoomTestRepository;
    private String hotelRoomId1;
    private String hotelRoomId2;

    @AfterEach
    void deleteHotelRooms() {
        springJpaHotelRoomTestRepository.deleteAll(asList(hotelRoomId1, hotelRoomId2));
    }

    @Test
    @Transactional
    void shouldReturnAllHotelRooms() {
        HotelRoom.Builder hotelRoom1 = hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(ROOM_NUMBER_1)
                .withSpacesDefinition(SPACES_DEFINITION_1)
                .withDescription(DESCRIPTION_1);
        hotelRoomId1 = existing(hotelRoom1);
        HotelRoom.Builder hotelRoom2 = hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(ROOM_NUMBER_2)
                .withSpacesDefinition(SPACES_DEFINITION_2)
                .withDescription(DESCRIPTION_2);
        hotelRoomId2 = existing(hotelRoom2);

        Iterable<HotelRoomReadModel> actual = queryHotelRoomRepository.findAll(HOTEL_ID);
        
        assertThat(actual)
                .hasSize(2)
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId1)
                            .hasHotelIdEqualTo(HOTEL_ID)
                            .hasNumberEqualTo(ROOM_NUMBER_1)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_1)
                            .hasDescriptionEqualTo(DESCRIPTION_1);
                })
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId2)
                            .hasHotelIdEqualTo(HOTEL_ID)
                            .hasNumberEqualTo(ROOM_NUMBER_2)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_2)
                            .hasDescriptionEqualTo(DESCRIPTION_2);
                });
    }

    private String existing(HotelRoom.Builder hotelRoom) {
        return hotelRoomRepository.save(hotelRoom.build());
    }
}