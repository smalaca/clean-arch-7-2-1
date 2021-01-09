package com.smalaca.rentalapplication.application.hotelroom;

import com.smalaca.rentalapplication.domain.booking.Booking;
import com.smalaca.rentalapplication.domain.booking.BookingRepository;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoom;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomEventsPublisher;
import com.smalaca.rentalapplication.domain.hotelroom.HotelRoomRepository;

import java.time.LocalDate;
import java.util.List;

import static com.smalaca.rentalapplication.domain.hotelroom.HotelRoom.Builder.hotelRoom;

public class HotelRoomApplicationService {
    private final HotelRoomRepository hotelRoomRepository;
    private final BookingRepository bookingRepository;
    private final HotelRoomEventsPublisher hotelRoomEventsPublisher;

    HotelRoomApplicationService(
            HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, HotelRoomEventsPublisher hotelRoomEventsPublisher) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingRepository = bookingRepository;
        this.hotelRoomEventsPublisher = hotelRoomEventsPublisher;
    }

    public String add(HotelRoomDto hotelRoomDto) {
        HotelRoom hotelRoom = hotelRoom()
                .withHotelId(hotelRoomDto.getHotelId())
                .withNumber(hotelRoomDto.getNumber())
                .withSpacesDefinition(hotelRoomDto.getSpacesDefinition())
                .withDescription(hotelRoomDto.getDescription())
                .build();

        return hotelRoomRepository.save(hotelRoom);
    }

    public String book(String hotelRoomId, String tenantId, List<LocalDate> days) {
        HotelRoom hotelRoom = hotelRoomRepository.findById(hotelRoomId);

        Booking booking = hotelRoom.book(tenantId, days, hotelRoomEventsPublisher);

        return bookingRepository.save(booking);
    }
}
