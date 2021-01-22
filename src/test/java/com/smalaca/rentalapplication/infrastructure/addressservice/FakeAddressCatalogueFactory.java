package com.smalaca.rentalapplication.infrastructure.addressservice;

import com.smalaca.rentalapplication.domain.address.AddressCatalogue;
import com.smalaca.rentalapplication.domain.address.AddressDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("FakeAddressCatalogue")
public class FakeAddressCatalogueFactory {
    @Bean
    @Primary
    public AddressCatalogue addressCatalogue() {
        return new AddressCatalogue() {
            @Override
            public boolean exists(AddressDto addressDto) {
                return true;
            }
        };
    }
}
