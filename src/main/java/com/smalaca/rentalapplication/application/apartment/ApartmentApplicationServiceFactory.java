package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.address.AddressCatalogue;
import com.smalaca.rentalapplication.domain.apartment.ApartmentDomainService;
import com.smalaca.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.smalaca.rentalapplication.domain.apartment.ApartmentFactory;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.clock.Clock;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import com.smalaca.rentalapplication.domain.owner.OwnerRepository;
import com.smalaca.rentalapplication.domain.tenant.TenantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {
    @Bean
    @SuppressWarnings("checkstyle:ParameterNumber")
    ApartmentApplicationService apartmentApplicationService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, OwnerRepository ownerRepository,
            TenantRepository tenantRepository, ApartmentOfferRepository apartmentOfferRepository, AddressCatalogue addressCatalogue,
            EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        ApartmentEventsPublisher apartmentEventsPublisher = new ApartmentEventsPublisher(eventIdFactory, clock, eventChannel);
        ApartmentFactory apartmentFactory = new ApartmentFactory(ownerRepository, addressCatalogue);
        ApartmentDomainService apartmentDomainService = new ApartmentDomainService(
                apartmentRepository, apartmentOfferRepository, bookingRepository, tenantRepository, apartmentEventsPublisher);

        return new ApartmentApplicationService(apartmentRepository, bookingRepository, apartmentFactory, apartmentDomainService);
    }
}
