package com.smalaca.rentalapplication.infrastructure.rest.api.hotelroom;

import com.smalaca.rentalapplication.application.hotelroom.HotelRoomApplicationService;
import com.smalaca.rentalapplication.query.hotelroom.HotelRoomReadModel;
import com.smalaca.rentalapplication.query.hotelroom.QueryHotelRoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
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
    public void add(@RequestBody HotelRoomDto hotelRoomDto) {
        hotelRoomApplicationService.add(
                hotelRoomDto.getHotelId(), hotelRoomDto.getNumber(), hotelRoomDto.getSpacesDefinition(), hotelRoomDto.getDescription());
    }

    @PutMapping("/book/{id}")
    public void book(@PathVariable String id, @RequestBody HotelBookingDto hotelBookingDto) {
        hotelRoomApplicationService.book(id, hotelBookingDto.getTenantId(), hotelBookingDto.getDays());
    }

    @GetMapping("/hotel/{hotelId}")
    public Iterable<HotelRoomReadModel> findAll(@PathVariable String hotelId) {
        return queryHotelRoomRepository.findAll(hotelId);
    }
}
