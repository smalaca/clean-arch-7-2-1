package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelEventsPublisher;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;

import static com.smalaca.rentalapplication.domain.hotel.Hotel.Builder.hotel;

public class HotelApplicationService {
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final HotelEventsPublisher hotelEventsPublisher;

    HotelApplicationService(HotelRepository hotelRepository, BookingRepository bookingRepository, HotelEventsPublisher hotelEventsPublisher) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
        this.hotelEventsPublisher = hotelEventsPublisher;
    }

    public String add(HotelDto hotelDto) {
        Hotel hotel = hotel()
                .withName(hotelDto.getName())
                .withStreet(hotelDto.getStreet())
                .withPostalCode(hotelDto.getPostalCode())
                .withBuildingNumber(hotelDto.getBuildingNumber())
                .withCity(hotelDto.getCity())
                .withCountry(hotelDto.getCountry())
                .build();

        return hotelRepository.save(hotel);
    }

    public void add(HotelRoomDto hotelRoomDto) {
        Hotel hotel = hotelRepository.findById(hotelRoomDto.getHotelId());

        hotel.addRoom(hotelRoomDto.getNumber(), hotelRoomDto.getSpacesDefinition(), hotelRoomDto.getDescription());

        hotelRepository.save(hotel);
    }

    public String book(HotelRoomBookingDto hotelRoomBookingDto) {
        Hotel hotel = hotelRepository.findById(hotelRoomBookingDto.getHotelId());

        Booking booking = hotel.bookRoom(
                hotelRoomBookingDto.getNumber(), hotelRoomBookingDto.getTenantId(), hotelRoomBookingDto.getDays(), hotelEventsPublisher);

        return bookingRepository.save(booking);
    }
}
