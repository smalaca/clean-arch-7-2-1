package com.smalaca.rentalapplication.infrastructure.persistence.jpa.owner;

import com.smalaca.rentalapplication.domain.owner.OwnerRepository;
import com.smalaca.usermanagement.infrastructure.persistence.jpa.user.SpringJpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaOwnerRepository implements OwnerRepository {
    private final SpringJpaUserRepository repository;

    JpaOwnerRepository(SpringJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(String ownerId) {
        return repository.existsById(UUID.fromString(ownerId));
    }
}
