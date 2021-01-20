package com.smalaca.usermanagement.domain.user;

import java.util.UUID;

public interface UserRepository {
    UUID save(User user);

    boolean existsWithLogin(String login);
}
