package com.smalaca.usermanagement.infrastructure.persistence.jpa.user;

import com.smalaca.usermanagement.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public
interface SpringJpaUserRepository extends CrudRepository<User, UUID> {
    boolean existsByLogin(String login);
}
