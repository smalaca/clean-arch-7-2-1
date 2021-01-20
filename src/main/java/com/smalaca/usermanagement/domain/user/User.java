package com.smalaca.usermanagement.domain.user;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "USER")
@SuppressWarnings("PMD.UnusedPrivateField")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String login;

    @Embedded
    private Name name;

    private User() {}

    User(String login, Name name) {
        this.login = login;
        this.name = name;
    }

    public UUID getId() {
        return id;
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
}
