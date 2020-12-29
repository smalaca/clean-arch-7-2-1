package com.smalaca.rentalapplication.infrastructure.rest.api.hotel;

import com.smalaca.rentalapplication.application.hotel.HotelApplicationService;
import com.smalaca.rentalapplication.query.hotel.HotelReadModel;
import com.smalaca.rentalapplication.query.hotel.QueryHotelRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/hotel")
public class HotelRestController {
    private final HotelApplicationService hotelApplicationService;
    private final QueryHotelRepository queryHotelRepository;

    public HotelRestController(HotelApplicationService hotelApplicationService, QueryHotelRepository queryHotelRepository) {
        this.hotelApplicationService = hotelApplicationService;
        this.queryHotelRepository = queryHotelRepository;
    }

    @PostMapping
    public void add(@RequestBody HotelDto hotelDto) {
        hotelApplicationService.add(
                hotelDto.getName(), hotelDto.getStreet(), hotelDto.getPostalCode(), hotelDto.getBuildingNumber(),
                hotelDto.getCity(), hotelDto.getCountry());
    }

    @GetMapping
    public Iterable<HotelReadModel> findAll() {
        return queryHotelRepository.findAll();
    }
}
