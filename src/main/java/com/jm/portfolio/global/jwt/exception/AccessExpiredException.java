package com.jm.portfolio.global.jwt.exception;

import com.jm.portfolio.global.error.exception.BusinessException;
import com.jm.portfolio.global.error.exception.ErrorCode;

public class AccessExpiredException extends BusinessException {

    public AccessExpiredException(String message) {
        super(message, ErrorCode.ACCESS_TOKEN_EXPIRED_EXCEPTION);
    }
}
