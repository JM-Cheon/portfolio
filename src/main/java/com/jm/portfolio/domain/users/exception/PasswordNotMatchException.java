package com.jm.portfolio.domain.users.exception;

import com.jm.portfolio.global.error.exception.ErrorCode;
import com.jm.portfolio.global.error.exception.InvalidValueException;

public class PasswordNotMatchException extends InvalidValueException {

    public PasswordNotMatchException() {
        super("Wrong password!", ErrorCode.LOGIN_FAILED);
    }
}
