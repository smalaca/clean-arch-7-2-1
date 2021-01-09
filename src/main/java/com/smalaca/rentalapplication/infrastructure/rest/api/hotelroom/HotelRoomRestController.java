package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import com.smalaca.rentalapplication.application.hotelroom.HotelBookingDto;
import com.smalaca.rentalapplication.application.hotelroom.HotelRoomApplicationService;
import com.smalaca.rentalapplication.application.hotelroom.HotelRoomDto;
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
    private final HotelRoomApplicationService hotelRoomApplicationService;
    private final QueryHotelRoomRepository queryHotelRoomRepository;

    public HotelRoomRestController(
            HotelRoomApplicationService hotelRoomApplicationService, QueryHotelRoomRepository queryHotelRoomRepository) {
        this.hotelRoomApplicationService = hotelRoomApplicationService;
        this.queryHotelRoomRepository = queryHotelRoomRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody HotelRoomDto hotelRoomDto) {
        String id = hotelRoomApplicationService.add(hotelRoomDto);

        return ResponseEntity.created(URI.create("/hotelroom/" + id)).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable String id, @RequestBody HotelBookingDto hotelBookingDto) {
        String bookingId = hotelRoomApplicationService.book(hotelBookingDto.getHotelRoomId(), hotelBookingDto.getTenantId(), hotelBookingDto.getDays());

        return ResponseEntity.created(URI.create("/booking/" + bookingId)).build();
    }

    @GetMapping("/hotel/{hotelId}")
    public Iterable<HotelRoomReadModel> findAll(@PathVariable String hotelId) {
        return queryHotelRoomRepository.findAll(hotelId);
    }
}
