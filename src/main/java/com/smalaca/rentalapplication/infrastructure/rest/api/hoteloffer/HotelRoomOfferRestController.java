package com.smalaca.rentalapplication.infrastructure.rest.api.hoteloffer;

import com.smalaca.rentalapplication.application.hotelroomoffer.HotelRoomOfferApplicationService;
import com.smalaca.rentalapplication.application.hotelroomoffer.HotelRoomOfferDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/hotelroomoffer")
public class HotelRoomOfferRestController {
    private final HotelRoomOfferApplicationService service;

    public HotelRoomOfferRestController(HotelRoomOfferApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody HotelRoomOfferDto hotelRoomOfferDto) {
        UUID id = service.add(hotelRoomOfferDto);

        return ResponseEntity.created(URI.create("/hotelroomoffer/" + id)).build();
    }
}
