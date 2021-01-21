package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.money.Money;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.tenant.TenantNotFoundException;
import com.smalaca.rentalapplication.domain.tenant.TenantRepository;

import java.math.BigDecimal;
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
                return bookApartment(newApartmentBookingDto);
            } else {
                throw new TenantNotFoundException(newApartmentBookingDto.getTenantId());
            }
        } else {
            throw new ApartmentNotFoundException(newApartmentBookingDto.getApartmentId());
        }
    }

    private Booking bookApartment(NewApartmentBookingDto newApartmentBookingDto) {
        Apartment apartment = apartmentRepository.findById(newApartmentBookingDto.getApartmentId());
        List<Booking> bookings = bookingRepository.findAllAcceptedBy(apartment.rentalPlaceIdentifier());
        Period period = Period.from(newApartmentBookingDto.getStart(), newApartmentBookingDto.getEnd());
        ApartmentBooking apartmentBooking = new ApartmentBooking(
                bookings, newApartmentBookingDto.getTenantId(), period, Money.of(BigDecimal.valueOf(42)), apartmentEventsPublisher);

        return apartment.book(apartmentBooking);
    }
}
