package com.smalaca.rentalapplication.infrastructure.rest.api.hoteloffer;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.application.hotelroom.HotelRoomDto;
import com.smalaca.rentalapplication.application.hotelroomoffer.HotelRoomOfferDto;
import com.smalaca.rentalapplication.infrastructure.json.JsonFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
class HotelRoomOfferRestControllerSystemTest {
    private static final BigDecimal PRICE = BigDecimal.valueOf(42);
    private static final LocalDate START = LocalDate.of(2040, 12, 10);
    private static final LocalDate END = LocalDate.of(2041, 12, 20);

    private final JsonFactory jsonFactory = new JsonFactory();
    @Autowired private MockMvc mockMvc;

    @Test
    void shouldCreateApartmentOfferForExistingApartment() throws Exception {
        String hotelRoomId = givenExistingHotelRoom();
        HotelRoomOfferDto dto = new HotelRoomOfferDto(hotelRoomId, PRICE, START, END);

        mockMvc.perform(post("/hotelroomoffer").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(dto)))
                .andExpect(status().isCreated());
    }

    private String givenExistingHotelRoom() throws Exception {
        return save(givenHotelRoom());
    }

    private HotelRoomDto givenHotelRoom() {
        return new HotelRoomDto("5678", 42, ImmutableMap.of("Room1", 30.0), "This is very nice place");
    }

    private String save(HotelRoomDto hotelRoomDto) throws Exception {
        return mockMvc.perform(post("/hotelroom").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(hotelRoomDto)))
                .andReturn()
                .getResponse().getRedirectedUrl().replace("/hotelroom/", "");
    }
}