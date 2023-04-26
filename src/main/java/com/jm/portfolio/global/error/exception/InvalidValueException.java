package com.jm.portfolio.global.error.exception;

import com.jm.portfolio.global.error.constants.ErrorCode;

public class InvalidValueException extends BusinessException{
    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
