package com.smalaca.usermanagement.infrastructure.rest.api;

import com.smalaca.rentalapplication.infrastructure.json.JsonFactory;
import com.smalaca.usermanagement.application.user.UserDto;
import com.smalaca.usermanagement.infrastructure.persistence.jpa.user.SpringJpaUserTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
class UserRestControllerSystemTest {
    private final JsonFactory jsonFactory = new JsonFactory();
    private String userId;

    @Autowired private MockMvc mockMvc;
    @Autowired private SpringJpaUserTestRepository springJpaUserTestRepository;

    @AfterEach
    void deleteApartmentOffers() {
        springJpaUserTestRepository.deleteById(UUID.fromString(userId));
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserDto userDto = new UserDto("scarletwitch", "Wanda", "Maximoff");

        MvcResult actual = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(userDto)))
                .andExpect(status().isCreated())
                .andReturn();

        userId = actual.getResponse().getRedirectedUrl().replace("/user/", "");
    }
}