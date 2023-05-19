package com.jm.portfolio.domain.model;

import lombok.Getter;

@Getter
public enum AuthorityEnum {

    GUEST("GUEST"),
    USER("USER"),
    INTERIM_ADMIN("INTERIM_ADMIN"),
    ADMIN("ADMIN");

    private String auth;

    AuthorityEnum(String auth) {
        this.auth = auth;
    }
}
