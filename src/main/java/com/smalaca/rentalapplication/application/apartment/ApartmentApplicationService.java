package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentDomainService;
import com.smalaca.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.smalaca.rentalapplication.domain.apartment.ApartmentFactory;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;

public class ApartmentApplicationService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;
    private final ApartmentFactory apartmentFactory;

    ApartmentApplicationService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, ApartmentEventsPublisher apartmentEventsPublisher, ApartmentFactory apartmentFactory) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
        this.apartmentFactory = apartmentFactory;
    }

    public String add(ApartmentDto apartmentDto) {
        Apartment apartment = apartmentFactory.create(apartmentDto.asNewApartmentDto());

        return apartmentRepository.save(apartment);
    }

    public String book(ApartmentBookingDto apartmentBookingDto) {
        Booking booking = new ApartmentDomainService(apartmentRepository, apartmentEventsPublisher).book(apartmentBookingDto.asNewApartmentBookingDto());

        return bookingRepository.save(booking);
    }
}
