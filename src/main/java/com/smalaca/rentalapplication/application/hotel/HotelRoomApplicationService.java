package com.smalaca.rentalapplication.application.hotel;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.hotel.Hotel;
import com.smalaca.rentalapplication.domain.hotel.HotelRepository;
import com.smalaca.rentalapplication.domain.hotel.HotelRoomEventsPublisher;

public class HotelRoomApplicationService {
    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;
    private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

    HotelRoomApplicationService(HotelRepository hotelRepository, BookingRepository bookingRepository, HotelRoomEventsPublisher hotelRoomEventsPublisher) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRoomEventsPublisher = hotelRoomEventsPublisher;
    }

    public String add(HotelRoomDto hotelRoomDto) {
        Hotel hotel = hotelRepository.findById(hotelRoomDto.getHotelId());

        hotel.addRoom(hotelRoomDto.getNumber(), hotelRoomDto.getSpacesDefinition(), hotelRoomDto.getDescription());

        hotelRepository.save(hotel);
        return hotel.getIdOfRoom(hotelRoomDto.getNumber());
    }

    public String book(HotelRoomBookingDto hotelRoomBookingDto) {
        Hotel hotel = hotelRepository.findById(hotelRoomBookingDto.getHotelId());

        Booking booking = hotel.bookRoom(hotelRoomBookingDto.getNumber(), hotelRoomBookingDto.getTenantId(), hotelRoomBookingDto.getDays(), hotelRoomEventsPublisher);

        return bookingRepository.save(booking);
    }
}
