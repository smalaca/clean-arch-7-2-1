package com.smalaca.usermanagement.infrastructure.persistence.jpa.user;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SpringJpaUserTestRepository {
    private final SpringJpaUserRepository repository;

    SpringJpaUserTestRepository(SpringJpaUserRepository repository) {
        this.repository = repository;
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}