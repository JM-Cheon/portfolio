package com.jm.portfolio.domain.users.exception;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.global.error.exception.ErrorCode;
import com.jm.portfolio.global.error.exception.InvalidValueException;

public class NicknameDuplicateException extends InvalidValueException {

    public NicknameDuplicateException(final String nickname) {
        super(nickname, ErrorCode.NICKNAME_DUPLICATION);
    }
}
