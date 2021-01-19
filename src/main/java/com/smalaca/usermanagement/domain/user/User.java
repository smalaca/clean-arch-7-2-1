package com.smalaca.usermanagement.domain.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User {
    private final String login;
    private final Name name;

    private User(String login, Name name) {
        this.login = login;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder().append(login, user.login).append(name, user.name).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(login).append(name).toHashCode();
    }

    public static class Builder {
        private String login;
        private Name name;

        public static Builder user() {
            return new Builder();
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withName(String name, String lastName) {
            this.name = new Name(name, lastName);
            return this;
        }

        public User build() {
            return new User(login, name);
        }
    }
}
