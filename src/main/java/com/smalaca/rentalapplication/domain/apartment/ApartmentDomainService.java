package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.tenant.TenantNotFoundException;
import com.smalaca.rentalapplication.domain.tenant.TenantRepository;

import java.util.List;

public class ApartmentDomainService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    private final TenantRepository tenantRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;

    public ApartmentDomainService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, TenantRepository tenantRepository,
            ApartmentEventsPublisher apartmentEventsPublisher) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
        this.tenantRepository = tenantRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
    }

    public Booking book(NewApartmentBookingDto newApartmentBookingDto) {
        if (apartmentRepository.existById(newApartmentBookingDto.getApartmentId())) {
            if (tenantRepository.existById(newApartmentBookingDto.getTenantId())) {
                Apartment apartment = apartmentRepository.findById(newApartmentBookingDto.getApartmentId());
                List<Booking> bookings = bookingRepository.findAllAcceptedBy(apartment.rentalPlaceIdentifier());
                Period period = new Period(newApartmentBookingDto.getStart(), newApartmentBookingDto.getEnd());

                return apartment.book(bookings, newApartmentBookingDto.getTenantId(), period, apartmentEventsPublisher);
            } else {
                throw new TenantNotFoundException(newApartmentBookingDto.getTenantId());
            }
        } else {
            throw new ApartmentNotFoundException(newApartmentBookingDto.getApartmentId());
        }
    }
}
