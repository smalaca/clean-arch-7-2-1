package com.smalaca.usermanagement.infrastructure.rest.api;

import com.smalaca.usermanagement.application.user.UserApplicationService;
import com.smalaca.usermanagement.application.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserApplicationService userApplicationService;

    public UserRestController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserDto userDto) {
        UUID id = userApplicationService.register(userDto);

        return ResponseEntity.created(URI.create("/user/" + id)).build();
    }
}
