package com.smalaca.rentalapplication.domain.owner;

public interface OwnerRepository {
    boolean exists(String ownerId);
}
