package com.smalaca.rentalapplication.infrastructure.rest.api.hoteloffer;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.application.hotel.HotelDto;
import com.smalaca.rentalapplication.application.hotel.HotelRoomDto;
import com.smalaca.rentalapplication.application.hotelroomoffer.HotelRoomOfferDto;
import com.smalaca.rentalapplication.infrastructure.json.JsonFactory;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotel.SpringJpaHotelTestRepository;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer.SpringJpaHotelRoomOfferTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
class HotelRoomOfferRestControllerSystemTest {
    private static final BigDecimal PRICE = BigDecimal.valueOf(42);
    private static final LocalDate START = LocalDate.of(2040, 12, 10);
    private static final LocalDate END = LocalDate.of(2041, 12, 20);
    private static final int ROOM_NUMBER = 42;

    private final JsonFactory jsonFactory = new JsonFactory();
    private final List<String> offerIds = new ArrayList<>();
    private String hotelId;

    @Autowired private MockMvc mockMvc;
    @Autowired private SpringJpaHotelTestRepository hotelRepository;
    @Autowired private SpringJpaHotelRoomOfferTestRepository hotelRoomOfferRepository;

    @BeforeEach
    void givenHotel() throws Exception {
        HotelDto hotelDto = new HotelDto("Big Hotel", "Florianska", "12-345", "13", "Cracow", "Poland");
        MvcResult result = mockMvc.perform(post("/hotel").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(hotelDto)))
                .andExpect(status().isCreated())
                .andReturn();

        hotelId = result.getResponse().getRedirectedUrl().replace("/hotel/", "");
    }

    @AfterEach
    void deleteHotelRooms() {
        hotelRepository.deleteById(hotelId);
        hotelRoomOfferRepository.deleteAll(offerIds);
    }

    @Test
    void shouldCreateApartmentOfferForExistingApartment() throws Exception {
        String hotelRoomId = givenExistingHotelRoom();
        HotelRoomOfferDto dto = new HotelRoomOfferDto(hotelId, ROOM_NUMBER, hotelRoomId, PRICE, START, END);

        MvcResult result = mockMvc.perform(post("/hotelroomoffer").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(dto)))
                .andExpect(status().isCreated())
                .andReturn();
        storeOfferId(result);
    }

    private void storeOfferId(MvcResult result) {
        offerIds.add(result.getResponse().getRedirectedUrl().replace("/hotelroomoffer/", ""));
    }

    private String givenExistingHotelRoom() throws Exception {
        return save(givenHotelRoom());
    }

    private HotelRoomDto givenHotelRoom() {
        return new HotelRoomDto(hotelId, ROOM_NUMBER, ImmutableMap.of("Room1", 30.0), "This is very nice place");
    }

    private String save(HotelRoomDto hotelRoomDto) throws Exception {
        return mockMvc.perform(post("/hotelroom").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(hotelRoomDto)))
                .andReturn()
                .getResponse().getRedirectedUrl().replace("/hotelroom/", "");
    }
}