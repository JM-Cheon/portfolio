package com.jm.portfolio.domain.model;

import lombok.Getter;

@Getter
public enum AuthorityEnum {

    GUEST("ROLE_GUEST"),
    USER("ROLE_USER"),
    INTERIM_ADMIN("ROLE_INTERIM_ADMIN"),
    ADMIN("ROLE_ADMIN");

    private String auth;

    AuthorityEnum(String auth) {
        this.auth = auth;
    }
}
