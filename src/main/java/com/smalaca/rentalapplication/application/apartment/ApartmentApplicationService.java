package com.smalaca.rentalapplication.application.apartment;

import com.smalaca.rentalapplication.domain.apartment.Apartment;
import com.smalaca.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.smalaca.rentalapplication.domain.apartment.ApartmentRepository;
import com.smalaca.rentalapplication.domain.apartment.Booking;
import com.smalaca.rentalapplication.domain.apartment.BookingRepository;
import com.smalaca.rentalapplication.domain.apartment.Period;
import com.smalaca.rentalapplication.domain.event.EventIdFactory;
import com.smalaca.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.smalaca.rentalapplication.domain.apartment.Apartment.Builder.apartment;

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
        ApartmentEventsPublisher publisher = new ApartmentEventsPublisher(new EventIdFactory(), eventChannel);

        Booking booking = apartment.book(tenantId, period, publisher);

        return bookingRepository.save(booking);
    }
}
