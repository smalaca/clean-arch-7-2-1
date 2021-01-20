package com.smalaca.rentalapplication.application.apartmentoffer;

import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferFactory;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApartmentOfferApplicationServiceFactory {
    @Bean
    public ApartmentOfferApplicationService apartmentOfferApplicationService(ApartmentOfferRepository apartmentOfferRepository, ApartmentRepository apartmentRepository) {
        ApartmentOfferFactory apartmentOfferFactory = new ApartmentOfferFactory(apartmentRepository);
        return new ApartmentOfferApplicationService(apartmentOfferRepository, apartmentOfferFactory);
    }
}
