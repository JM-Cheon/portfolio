package com.jm.portfolio.domain.model;

import lombok.Getter;

@Getter
public enum AuthEnum {

    USER("USER"),
    ADMIN("ADMIN");

    private String auth;

    AuthEnum(String auth) {
        this.auth = auth;
    }
}
