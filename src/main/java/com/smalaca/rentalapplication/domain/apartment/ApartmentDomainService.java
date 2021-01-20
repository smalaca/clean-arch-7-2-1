package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.period.Period;

public class ApartmentDomainService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;

    public ApartmentDomainService(ApartmentRepository apartmentRepository, ApartmentEventsPublisher apartmentEventsPublisher) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
    }

    public Booking book(NewApartmentBookingDto newApartmentBookingDto) {
        Apartment apartment = apartmentRepository.findById(newApartmentBookingDto.getApartmentId());
        Period period = new Period(newApartmentBookingDto.getStart(), newApartmentBookingDto.getEnd());

        return apartment.book(newApartmentBookingDto.getTenantId(), period, apartmentEventsPublisher);
    }
}
