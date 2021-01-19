package com.smalaca.usermanagement.application.user;

import com.smalaca.usermanagement.domain.user.User;
import com.smalaca.usermanagement.domain.user.UserAssertion;
import com.smalaca.usermanagement.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class UserApplicationServiceTest {
    private static final String LOGIN = "smalaca";
    private static final String NAME = "Sebastian";
    private static final String LAST_NAME = "Malaca";

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserApplicationService service = new UserApplicationService(userRepository);

    @Test
    void shouldRegisterUser() {
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        UserDto dto = new UserDto(LOGIN, NAME, LAST_NAME);

        service.register(dto);

        then(userRepository).should().save(captor.capture());
        UserAssertion.assertThat(captor.getValue()).represents(LOGIN, NAME, LAST_NAME);
    }
}
