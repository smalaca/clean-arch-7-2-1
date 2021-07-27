package com.smalaca.rentalapplication.infrastructure.rest.api.client;

import com.smalaca.rentalapplication.application.apartment.ApartmentBookingDto;
import com.smalaca.rentalapplication.application.apartment.ApartmentDto;
import com.smalaca.rentalapplication.application.apartmentoffer.ApartmentOfferDto;
import com.smalaca.rentalapplication.infrastructure.json.JsonFactory;
import com.smalaca.usermanagement.application.user.UserDto;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    public ResultActions create(ApartmentDto apartmentDto) {
        try {
            return mockMvc.perform(post("/apartment").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(apartmentDto)));
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }

    public String createAndReturnId(ApartmentDto apartmentDto) {
        try {
            return mockMvc.perform(post("/apartment").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(apartmentDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse().getRedirectedUrl().replace("/apartment/", "");
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }

    public ResultActions findApartmentById(String apartmentId) {
        try {
            return mockMvc.perform(get("/apartment/" + apartmentId));
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }

    public void create(ApartmentOfferDto apartmentOfferDto) {
        try {
            mockMvc.perform(post("/apartmentoffer").contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(apartmentOfferDto)));
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }

    public String createAndReturnId(String apartmentId, ApartmentBookingDto apartmentBookingDto) {
        try {
            return mockMvc.perform(put("/apartment/book/" + apartmentId).contentType(MediaType.APPLICATION_JSON).content(jsonFactory.create(apartmentBookingDto)))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse().getRedirectedUrl().replace("/booking/", "");
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }

    public ResultActions findAllApartments() {
        try {
            return mockMvc.perform(get("/apartment"));
        } catch (Exception exception) {
            throw new RentalApplicationClientException(exception);
        }
    }
}
