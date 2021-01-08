package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartment.Booking;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;
import com.smalaca.rentalapplication.domain.apartment.Period;

import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;

public class ApartmentApplicationService {
    private final ApartmentRepository apartmentRepository;
    private final BookingRepository bookingRepository;
    private final ApartmentEventsPublisher apartmentEventsPublisher;

    ApartmentApplicationService(ApartmentRepository apartmentRepository, BookingRepository bookingRepository, ApartmentEventsPublisher apartmentEventsPublisher) {
        this.apartmentRepository = apartmentRepository;
        this.bookingRepository = bookingRepository;
        this.apartmentEventsPublisher = apartmentEventsPublisher;
    }

    public String add(ApartmentDto apartmentDto) {
        Apartment apartment = apartment()
                .withOwnerId(apartmentDto.getOwnerId())
                .withStreet(apartmentDto.getStreet())
                .withPostalCode(apartmentDto.getPostalCode())
                .withHouseNumber(apartmentDto.getHouseNumber())
                .withApartmentNumber(apartmentDto.getApartmentNumber())
                .withCity(apartmentDto.getCity())
                .withCountry(apartmentDto.getCountry())
                .withDescription(apartmentDto.getDescription())
                .withRoomsDefinition(apartmentDto.getRoomsDefinition())
                .build();

        return apartmentRepository.save(apartment);
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    public String book(String apartmentId, String tenantId, LocalDate start, LocalDate end) {
        Apartment apartment = apartmentRepository.findById(apartmentId);
        Period period = new Period(start, end);

        Booking booking = apartment.book(tenantId, period, apartmentEventsPublisher);

        return bookingRepository.save(booking);
    }
}
