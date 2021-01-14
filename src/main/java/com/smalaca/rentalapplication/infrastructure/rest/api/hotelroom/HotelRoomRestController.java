package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import com.smalaca.rentalapplication.application.hotel.HotelApplicationService;
import com.smalaca.rentalapplication.application.hotel.HotelRoomBookingDto;
import com.smalaca.rentalapplication.application.hotel.HotelRoomDto;
import com.smalaca.rentalapplication.query.hotelroom.HotelRoomReadModel;
import com.smalaca.rentalapplication.query.hotelroom.QueryHotelRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomRestController {
    private final HotelApplicationService hotelApplicationService;
    private final QueryHotelRoomRepository queryHotelRoomRepository;

    public HotelRoomRestController(HotelApplicationService hotelApplicationService, QueryHotelRoomRepository queryHotelRoomRepository) {
        this.hotelApplicationService = hotelApplicationService;
        this.queryHotelRoomRepository = queryHotelRoomRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody HotelRoomDto hotelRoomDto) {
        String id = hotelApplicationService.add(hotelRoomDto);

        return ResponseEntity.created(URI.create("/hotelroom/" + id)).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable String id, @RequestBody HotelRoomBookingDto hotelRoomBookingDto) {
        String bookingId = hotelApplicationService.book(hotelRoomBookingDto);

        return ResponseEntity.created(URI.create("/booking/" + bookingId)).build();
    }

    @GetMapping("/hotel/{hotelId}")
    public Iterable<HotelRoomReadModel> findAll(@PathVariable String hotelId) {
        return queryHotelRoomRepository.findAll(hotelId);
    }
}
