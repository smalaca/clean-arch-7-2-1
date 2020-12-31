package com.smalaca.rentalapplication.infrastructure.rest.api.hotel;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class HotelRestControllerSystemTest {
    @Autowired private MockMvc mockMvc;

    @Test
    void shouldReturnNothingWhenThereWasNoHotelCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hotel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]", Matchers.hasSize(0)));
    }
}