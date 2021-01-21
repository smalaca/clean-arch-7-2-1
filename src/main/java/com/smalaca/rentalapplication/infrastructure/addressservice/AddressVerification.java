package com.smalaca.rentalapplication.infrastructure.addressservice;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddressVerification {
    private static final String VALID = "VALID";
    private String status;

    boolean isValid() {
        return VALID.equals(status);
    }
}
