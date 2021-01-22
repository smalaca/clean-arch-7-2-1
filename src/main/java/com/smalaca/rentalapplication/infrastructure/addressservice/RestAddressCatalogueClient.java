package com.smalaca.rentalapplication.infrastructure.addressservice;

import com.smalaca.rentalapplication.domain.address.AddressCatalogue;
import com.smalaca.rentalapplication.domain.address.AddressDto;
import org.springframework.stereotype.Component;

@Component
class RestAddressCatalogueClient implements AddressCatalogue {
    @Override
    public boolean exists(AddressDto addressDto) {
        return true;
    }
}
