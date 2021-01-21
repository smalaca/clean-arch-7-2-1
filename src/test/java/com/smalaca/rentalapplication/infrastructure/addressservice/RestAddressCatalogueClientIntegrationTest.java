package com.smalaca.rentalapplication.infrastructure.addressservice;

import com.smalaca.rentalapplication.domain.address.AddressDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("IntegrationTest")
class RestAddressCatalogueClientIntegrationTest {
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";

    @Test
    void shouldAlwaysReturnTrue() {
        AddressDto addressDto = new AddressDto(STREET, POSTAL_CODE, HOUSE_NUMBER, CITY, COUNTRY);

        boolean actual = new RestAddressCatalogueClient().exists(addressDto);

        assertThat(actual).isTrue();
    }
}