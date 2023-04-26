package com.jm.portfolio.domain.users.exception;

import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.global.error.constants.ErrorCode;
import com.jm.portfolio.global.error.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

    public EmailDuplicateException(final Email email) {
        super(email.getValue(), ErrorCode.EMAIL_DUPLICATION);
    }
}
