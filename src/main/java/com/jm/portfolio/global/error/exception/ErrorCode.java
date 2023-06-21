package com.jm.portfolio.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // Token
    TOKEN_EXCEPTION(401, "T001", "Invalid Token"),
    ACCESS_TOKEN_EXPIRED_EXCEPTION(401, "T002", "Access Token Expired"),
    REFRESH_TOKEN_EXPIRED_EXCEPTION(401, "T003", "Refresh Token Expired"),


        // Users
    EMAIL_DUPLICATION(400, "M001", "Email is Duplication"),
    NICKNAME_DUPLICATION(400, "M002", "Nickname is Duplication"),
    LOGIN_FAILED(400, "M003", "Login failed"),
    WRONG_PASSWORD(400, "M004", "Wrong password"),
    ;

    private int status;
    private final String code;
    private final String message;
    ErrorCode(int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
