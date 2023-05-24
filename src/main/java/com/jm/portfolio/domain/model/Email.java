package com.jm.portfolio.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"email"})
public class Email {

    @javax.validation.constraints.Email
    @Column(name = "email", length = 50)
    @NotEmpty
    private String value;

    private Email(final String email) {
        this.value = email;
    }

    public static Email of(final String email) {
        return new Email(email);
    }

    public String getHost() {
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(index + 1);
    }

    public String getId() {
        final int index = value.indexOf("@");
        return index == -1 ? null : value.substring(0, index);
    }
}
