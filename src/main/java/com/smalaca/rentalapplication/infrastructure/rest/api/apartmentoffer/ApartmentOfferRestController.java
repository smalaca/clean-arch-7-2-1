package com.smalaca.rentalapplication.infrastructure.rest.api.apartmentoffer;

import com.smalaca.rentalapplication.application.apartmentoffer.ApartmentOfferApplicationService;
import com.smalaca.rentalapplication.application.apartmentoffer.ApartmentOfferDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/apartmentoffer")
public class ApartmentOfferRestController {
    private final ApartmentOfferApplicationService service;

    public ApartmentOfferRestController(ApartmentOfferApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ApartmentOfferDto apartmentOfferDto) {
        UUID id = service.add(apartmentOfferDto);

        return ResponseEntity.created(URI.create("/apartmentoffer/" + id)).build();
    }
}
