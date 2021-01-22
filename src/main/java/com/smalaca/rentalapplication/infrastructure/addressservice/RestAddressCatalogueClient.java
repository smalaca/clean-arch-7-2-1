package com.smalaca.rentalapplication.infrastructure.addressservice;

import com.smalaca.rentalapplication.domain.address.AddressCatalogue;
import com.smalaca.rentalapplication.domain.address.AddressDto;
import org.springframework.web.client.RestTemplate;

class RestAddressCatalogueClient implements AddressCatalogue {
    private final RestTemplate restTemplate;
    private final String url;

    RestAddressCatalogueClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public boolean exists(AddressDto addressDto) {
        AddressVerification verification = restTemplate.postForObject(url + "/address/verify", addressDto, AddressVerification.class);

        return verification.isValid();
    }
}
