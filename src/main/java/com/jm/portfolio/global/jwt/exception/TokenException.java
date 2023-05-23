package com.jm.portfolio.global.jwt.exception;

import com.jm.portfolio.global.error.exception.BusinessException;
import com.jm.portfolio.global.error.exception.ErrorCode;

public class TokenException extends BusinessException {

    public TokenException(String message) {
        super(message, ErrorCode.TOKEN_EXCEPTION);
    }
}
