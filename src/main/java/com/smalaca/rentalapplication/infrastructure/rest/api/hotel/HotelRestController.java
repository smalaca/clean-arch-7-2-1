package com.smalaca.rentalapplication.infrastructure.rest.api.hotel;

import com.smalaca.rentalapplication.application.hotel.HotelApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelRestController {
    private final HotelApplicationService hotelApplicationService;

    public HotelRestController(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @PostMapping
    public void add(@RequestBody HotelDto hotelDto) {
        hotelApplicationService.add(
                hotelDto.getName(), hotelDto.getStreet(), hotelDto.getPostalCode(), hotelDto.getBuildingNumber(),
                hotelDto.getCity(), hotelDto.getCountry());
    }
}
