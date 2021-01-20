package com.smalaca.usermanagement.infrastructure.persistence.jpa.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaUserRepository implements UserRepository {
    private final SpringJpaUserRepository repository;

    JpaUserRepository(SpringJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(User user) {
        return repository.save(user).getId();
    }

    @Override
    public boolean existsWithLogin(String login) {
        return repository.existsByLogin(login);
    }
}
