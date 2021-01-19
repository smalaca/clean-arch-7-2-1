package com.smalaca.usermanagement.application.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private final String login;
    private final String name;
    private final String lastName;
}
