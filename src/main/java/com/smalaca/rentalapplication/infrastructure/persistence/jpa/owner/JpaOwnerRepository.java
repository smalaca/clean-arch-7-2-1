package com.smalaca.rentalapplication.infrastructure.persistence.jpa.owner;

import com.smalaca.rentalapplication.domain.owner.OwnerRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaOwnerRepository implements OwnerRepository {
    @Override
    public boolean exists(String ownerId) {
        return true;
    }
}
