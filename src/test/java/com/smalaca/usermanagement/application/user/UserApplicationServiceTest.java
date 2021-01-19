package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserAssertion;
import com.smalaca.usermanagement.domain.user.UserExistingException;
import com.smalaca.usermanagement.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class UserApplicationServiceTest {
    private static final String LOGIN = "smalaca";
    private static final String NAME = "Sebastian";
    private static final String LAST_NAME = "Malaca";

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserApplicationService service = new UserApplicationServiceFactory().userApplicationService(userRepository);

    @Test
    void shouldRegisterUser() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        givenNotExistingUserWithLogin(LOGIN);

        service.register(givenDto());

        then(userRepository).should().save(captor.capture());
        UserAssertion.assertThat(captor.getValue()).represents(LOGIN, NAME, LAST_NAME);
    }

    private void givenNotExistingUserWithLogin(String login) {
        given(userRepository.existsWithLogin(login)).willReturn(false);
    }

    @Test
    void shouldNotRegisterUserWhenUserWithGivenLoginAlreadyExists() {
        givenExistingUserWithLogin(LOGIN);

        UserExistingException actual = assertThrows(UserExistingException.class, () -> service.register(givenDto()));

        assertThat(actual).hasMessage("User with login " + LOGIN + " already exists.");
        then(userRepository).should(never()).save(any());
    }

    private UserDto givenDto() {
        return new UserDto(LOGIN, NAME, LAST_NAME);
    }

    private void givenExistingUserWithLogin(String login) {
        given(userRepository.existsWithLogin(login)).willReturn(true);
    }
}
