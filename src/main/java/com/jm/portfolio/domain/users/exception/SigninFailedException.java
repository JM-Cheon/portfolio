package com.jm.portfolio.domain.users.exception;

import com.jm.portfolio.global.error.exception.ErrorCode;
import com.jm.portfolio.global.error.exception.InvalidValueException;

public class SigninFailedException extends InvalidValueException {

    public SigninFailedException() {
        super("Sign-in failed!", ErrorCode.LOGIN_INPUT_INVALID);
    }
}
