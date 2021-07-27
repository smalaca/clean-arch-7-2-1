package com.smalaca.rentalapplication.infrastructure.rest.api.apartment;

import com.google.common.collect.ImmutableMap;
import com.smalaca.rentalapplication.application.apartment.ApartmentBookingDto;
import com.smalaca.rentalapplication.application.apartment.ApartmentDto;
import com.smalaca.rentalapplication.application.apartmentoffer.ApartmentOfferDto;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartment.SpringJpaApartmentTestRepository;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory.SpringJpaApartmentBookingHistoryTestRepository;
import com.smalaca.rentalapplication.infrastructure.persistence.jpa.booking.SpringJpaBookingTestRepository;
import com.smalaca.rentalapplication.infrastructure.rest.api.client.RentalApplicationClient;
import com.smalaca.usermanagement.application.user.UserDto;
import com.smalaca.usermanagement.infrastructure.persistence.jpa.user.SpringJpaUserTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("SystemTest")
@ActiveProfiles("FakeAddressCatalogue")
class ApartmentRestControllerSystemTest {
    private static final String STREET_1 = "Florianska";
    private static final String POSTAL_CODE_1 = "12-345";
    private static final String HOUSE_NUMBER_1 = "1";
    private static final String APARTMENT_NUMBER_1 = "13";
    private static final String CITY_1 = "Cracow";
    private static final String COUNTRY_1 = "Poland";
    private static final String DESCRIPTION_1 = "Nice place to stay";
    private static final Map<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);
    private static final String STREET_2 = "Grodzka";
    private static final String POSTAL_CODE_2 = "54-321";
    private static final String HOUSE_NUMBER_2 = "13";
    private static final String APARTMENT_NUMBER_2 = "42";
    private static final String CITY_2 = "Berlin";
    private static final String COUNTRY_2 = "Germany";
    private static final String DESCRIPTION_2 = "Lovely place";
    private static final Map<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("Toilet", 15.0, "RoomOne", 20.0, "RoomTwo", 25.0);
    private static final BigDecimal PRICE = BigDecimal.valueOf(123);
    private static final LocalDate START = LocalDate.of(2030, 10, 11);
    private static final LocalDate END = LocalDate.of(2050, 10, 20);

    private final List<String> apartmentIds = new ArrayList<>();
    private final List<String> apartmentBookingHistoryIds = new ArrayList<>();
    private final List<String> bookingIds = new ArrayList<>();
    private String ownerId1;
    private String ownerId2;
    private String tenantId;

    @Autowired private MockMvc mockMvc;
    @Autowired private SpringJpaApartmentTestRepository apartmentRepository;
    @Autowired private SpringJpaApartmentBookingHistoryTestRepository apartmentBookingHistoryRepository;
    @Autowired private SpringJpaBookingTestRepository bookingRepository;
    @Autowired private SpringJpaUserTestRepository springJpaUserTestRepository;
    private RentalApplicationClient client;

    @BeforeEach
    void givenExistingOwner() {
        client = RentalApplicationClient.create(mockMvc);

        ownerId1 = client.createAndReturnId(new UserDto("captain-america", "Steve", "Rogers"));
        ownerId2 = client.createAndReturnId(new UserDto("1R0N M4N", "Tony", "Stark"));
        tenantId = client.createAndReturnId(new UserDto("scarletwitch", "Wanda", "Maximoff"));
    }

    @AfterEach
    void deleteApartments() {
        apartmentRepository.deleteAll(apartmentIds);
        apartmentBookingHistoryRepository.deleteAll(apartmentBookingHistoryIds);
        bookingRepository.deleteAll(bookingIds);
        springJpaUserTestRepository.deleteById(ownerId1);
        springJpaUserTestRepository.deleteById(ownerId2);
        springJpaUserTestRepository.deleteById(tenantId);
    }

    @Test
    void shouldReturnNothingWhenApartmentDoesNotExist() throws Exception {
        String notExistingId = UUID.randomUUID().toString();

        ResultActions actual = client.findApartmentById(notExistingId);

        actual
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apartment").isEmpty())
                .andExpect(jsonPath("$.bookingHistory").isEmpty());
    }

    @Test
    void shouldReturnExistingApartment() throws Exception {
        String apartmentId = client.createAndReturnId(givenApartment1());
        apartmentIds.add(apartmentId);

        ResultActions actual = client.findApartmentById(apartmentId);

        actual
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apartment.ownerId").value(ownerId1))
                .andExpect(jsonPath("$.apartment.street").value(STREET_1))
                .andExpect(jsonPath("$.apartment.postalCode").value(POSTAL_CODE_1))
                .andExpect(jsonPath("$.apartment.houseNumber").value(HOUSE_NUMBER_1))
                .andExpect(jsonPath("$.bookingHistory").isEmpty());
    }

    @Test
    void shouldBookApartment() throws Exception {
        String apartmentId = client.createAndReturnId(givenApartment1());
        client.create(new ApartmentOfferDto(apartmentId, PRICE, START, END));
        apartmentBookingHistoryIds.add(apartmentId);

        ApartmentBookingDto apartmentBookingDto = new ApartmentBookingDto(apartmentId, tenantId, LocalDate.of(2040, 11, 12), LocalDate.of(2040, 12, 1));
        bookingIds.add(client.createAndReturnId(apartmentId, apartmentBookingDto));

        client.findApartmentById(apartmentId)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingHistory.bookings.[*]", hasSize(1)))
                .andExpect(jsonPath("$.bookingHistory.bookings.[0].tenantId").value(tenantId))
                .andExpect(jsonPath("$.bookingHistory.bookings.[0].periodStart").value("2040-11-12"))
                .andExpect(jsonPath("$.bookingHistory.bookings.[0].periodEnd").value("2040-12-01"));
    }

    @Test
    void shouldReturnAllApartments() throws Exception {
        apartmentIds.add(client.createAndReturnId(givenApartment1()));
        apartmentIds.add(client.createAndReturnId(givenApartment2()));

        ResultActions actual = client.findAllApartments();

        actual
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldValidateApartment() throws Exception {
        ApartmentDto apartmentDto = new ApartmentDto(ownerId1, null, "POSTAL_CODE_1", null, null, null, null, DESCRIPTION_1, SPACES_DEFINITION_1);

        ResultActions actual = client.create(apartmentDto);

        actual
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", is("country cannot be empty")))
                .andExpect(jsonPath("$.city", is("city cannot be empty")))
                .andExpect(jsonPath("$.street", is("street cannot be empty")))
                .andExpect(jsonPath("$.houseNumber", is("house number cannot be empty")))
                .andExpect(jsonPath("$.apartmentNumber", is("apartment number cannot be empty")))
                .andExpect(jsonPath("$.postalCode", is("postal code should be in form xx-xxx and contain only numbers")));
    }

    private ApartmentDto givenApartment1() {
        return new ApartmentDto(ownerId1, STREET_1, POSTAL_CODE_1, HOUSE_NUMBER_1, APARTMENT_NUMBER_1, CITY_1, COUNTRY_1, DESCRIPTION_1, SPACES_DEFINITION_1);
    }

    private ApartmentDto givenApartment2() {
        return new ApartmentDto(ownerId2, STREET_2, POSTAL_CODE_2, HOUSE_NUMBER_2, APARTMENT_NUMBER_2, CITY_2, COUNTRY_2, DESCRIPTION_2, SPACES_DEFINITION_2);
    }
}