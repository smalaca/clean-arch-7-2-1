package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentFactory;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartment.Booking;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;
import com.smalaca.rentalapplication.domain.apartment.Period;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ApartmentApplicationService {
    private final ApartmentRepository apartmentRepository;
    private final EventChannel eventChannel;
    private final BookingRepository bookingRepository;

    public ApartmentApplicationService(
            ApartmentRepository apartmentRepository, EventChannel eventChannel, BookingRepository bookingRepository) {
        this.apartmentRepository = apartmentRepository;
        this.eventChannel = eventChannel;
        this.bookingRepository = bookingRepository;
    }

    public String add(ApartmentDto apartmentDto) {
        Apartment apartment = new ApartmentFactory().create(
                apartmentDto.getOwnerId(), apartmentDto.getStreet(), apartmentDto.getPostalCode(), apartmentDto.getHouseNumber(),
                apartmentDto.getApartmentNumber(), apartmentDto.getCity(), apartmentDto.getCountry(), apartmentDto.getDescription(),
                apartmentDto.getRoomsDefinition());

        return apartmentRepository.save(apartment);
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    public String book(String apartmentId, String tenantId, LocalDate start, LocalDate end) {
        Apartment apartment = apartmentRepository.findById(apartmentId);
        Period period = new Period(start, end);

        Booking booking = apartment.book(tenantId, period, eventChannel);

        return bookingRepository.save(booking);
    }
}
