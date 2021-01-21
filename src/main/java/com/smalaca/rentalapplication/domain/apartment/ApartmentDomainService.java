package com.smalaca.rentalapplication.domain.apartment;

import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOffer;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferNotFoundException;
import com.smalaca.rentalapplication.domain.apartmentoffer.ApartmentOfferRepository;
import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.period.Period;
import com.smalaca.rentalapplication.domain.tenant.TenantNotFoundException;
import com.smalaca.rentalapplication.domain.tenant.TenantRepository;

import java.util.List;

public class ApartmentDomainService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentOfferRepository apartmentOfferRepository;
    private final BookingRepository bookingRepository;
    private final TenantRepository tenantRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;

    public ApartmentDomainService(
            ApartmentRepository apartmentRepository, ApartmentOfferRepository apartmentOfferRepository, BookingRepository bookingRepository,
            TenantRepository tenantRepository, ApartmentEventsPublisher apartmentEventsPublisher) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentOfferRepository = apartmentOfferRepository;
        this.bookingRepository = bookingRepository;
        this.tenantRepository = tenantRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
    }

    public Booking book(NewApartmentBookingDto newApartmentBookingDto) {
        verifyExistenceOfAggregates(newApartmentBookingDto);
        return bookApartment(newApartmentBookingDto);
    }

    private Booking bookApartment(NewApartmentBookingDto newApartmentBookingDto) {
        Apartment apartment = apartmentRepository.findById(newApartmentBookingDto.getApartmentId());
        List<Booking> bookings = bookingRepository.findAllAcceptedBy(apartment.rentalPlaceIdentifier());
        ApartmentOffer apartmentOffer = apartmentOfferRepository.findByApartmentId(newApartmentBookingDto.getApartmentId());
        Period period = Period.from(newApartmentBookingDto.getStart(), newApartmentBookingDto.getEnd());
        ApartmentBooking apartmentBooking = new ApartmentBooking(
                bookings, newApartmentBookingDto.getTenantId(), period, apartmentOffer.getMoney(), apartmentEventsPublisher);

        return apartment.book(apartmentBooking);
    }

    private void verifyExistenceOfAggregates(NewApartmentBookingDto newApartmentBookingDto) {
        if (!apartmentRepository.existById(newApartmentBookingDto.getApartmentId())) {
            throw new ApartmentNotFoundException(newApartmentBookingDto.getApartmentId());
        }

        if (!tenantRepository.existById(newApartmentBookingDto.getTenantId())) {
            throw new TenantNotFoundException(newApartmentBookingDto.getTenantId());
        }

        if (!apartmentOfferRepository.existByApartmentId(newApartmentBookingDto.getApartmentId())) {
            throw new ApartmentOfferNotFoundException(newApartmentBookingDto.getApartmentId());
        }
    }
}
