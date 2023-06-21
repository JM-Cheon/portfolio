package com.jm.portfolio.global.jwt.exception;

import com.jm.portfolio.global.error.exception.BusinessException;
import com.jm.portfolio.global.error.exception.ErrorCode;

public class RefreshExpiredException extends BusinessException {

    public RefreshExpiredException(String message) {
        super(message, ErrorCode.REFRESH_TOKEN_EXPIRED_EXCEPTION);
    }
}
