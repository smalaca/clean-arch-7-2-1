package com.smalaca.usermanagement.infrastructure.persistence.jpa.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserRepository;
import com.smalaca.usermanagement.domain.user.UserTestFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaUserRepositoryIntegrationTest {
    private static final String LOGIN = "smalaca";
    private static final String NAME = "Sebastian";
    private static final String LAST_NAME = "Malaca";

    @Autowired private UserRepository userRepository;
    @Autowired private SpringJpaUserRepository springJpaUserRepository;
    private UUID id;

    @AfterEach
    void deleteUser() {
        if (id != null) {
            springJpaUserRepository.deleteById(id);
        }
    }

    @Test
    void shouldCreateUser() {
        id = userRepository.save(givenUser());

        User actual = springJpaUserRepository.findById(id).get();
        assertThat(actual).isEqualTo(givenUser());
    }

    private User givenUser() {
        return new UserTestFactory().create(LOGIN, NAME, LAST_NAME);
    }

    @Test
    void shouldRecognizeUserWithGivenLoginDoesNotExist() {
        boolean actual = userRepository.existsWithLogin(LOGIN);

        assertThat(actual).isFalse();
    }

    @Test
    void shouldRecognizeUserWithGivenLoginExists() {
        User user = givenUser();
        id = userRepository.save(user);

        boolean actual = userRepository.existsWithLogin(LOGIN);

        assertThat(actual).isTrue();
    }
}