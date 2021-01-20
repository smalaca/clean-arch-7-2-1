package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentDomainService;
import com.smalaca.rentalapplication.domain.apartment.ApartmentFactory;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;

public class ApartmentApplicationService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    private final ApartmentFactory apartmentFactory;
    private final ApartmentDomainService apartmentDomainService;

    ApartmentApplicationService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, ApartmentFactory apartmentFactory, ApartmentDomainService apartmentDomainService) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
        this.apartmentFactory = apartmentFactory;
        this.apartmentDomainService = apartmentDomainService;
    }

    public String add(ApartmentDto apartmentDto) {
        Apartment apartment = apartmentFactory.create(apartmentDto.asNewApartmentDto());

        return apartmentRepository.save(apartment);
    }

    public String book(ApartmentBookingDto apartmentBookingDto) {
        Booking booking = apartmentDomainService.book(apartmentBookingDto.asNewApartmentBookingDto());

        return bookingRepository.save(booking);
    }
}
