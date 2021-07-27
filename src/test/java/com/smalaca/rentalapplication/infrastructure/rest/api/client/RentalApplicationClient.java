package com.smalaca.rentalapplication.infrastructure.rest.api.client;

import com.smalaca.rentalapplication.infrastructure.json.JsonFactory;
import com.smalaca.usermanagement.application.user.UserDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RentalApplicationClient {
    private final MockMvc mockMvc;
    private final JsonFactory jsonFactory;

    private RentalApplicationClient(MockMvc mockMvc, JsonFactory jsonFactory) {
        this.mockMvc = mockMvc;
        this.jsonFactory = jsonFactory;
    }

    public static RentalApplicationClient create(MockMvc mockMvc) {
        return new RentalApplicationClient(mockMvc, new JsonFactory());
    }

    public String createAndReturnId(UserDto userDto) {
        try {
            return mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(userDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse().getRedirectedUrl().replace("/user/", "");
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }
}
