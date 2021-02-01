package com.smalaca.rentalapplication.infrastructure.persistence.jpa.owner;

import com.smalaca.rentalapplication.domain.owner.OwnerRepository;
import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserRepository;
import com.smalaca.usermanagement.domain.user.UserTestFactory;
import com.smalaca.usermanagement.infrastructure.persistence.jpa.user.SpringJpaUserTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaOwnerRepositoryIntegrationTest {
    private static final String LOGIN = "smalaca";
    private static final String NAME = "Sebastian";
    private static final String LAST_NAME = "Malaca";

    @Autowired private UserRepository userRepository;
    @Autowired private SpringJpaUserTestRepository springJpaUserRepository;
    @Autowired private OwnerRepository repository;
    private UUID id;

    @AfterEach
    void deleteUser() {
        if (id != null) {
            springJpaUserRepository.deleteById(id);
        }
    }

    @Test
    void shouldRecognizeTenantDoesNotExist() {
        boolean actual = repository.exists(UUID.randomUUID().toString());

        assertThat(actual).isFalse();
    }

    @Test
    void shouldRecognizeTenantExists() {
        id = userRepository.save(givenUser());

        boolean actual = repository.exists(id.toString());

        assertThat(actual).isTrue();
    }

    private User givenUser() {
        return new UserTestFactory().create(LOGIN, NAME, LAST_NAME);
    }
}