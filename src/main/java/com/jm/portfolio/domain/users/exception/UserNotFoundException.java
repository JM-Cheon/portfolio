package com.jm.portfolio.domain.users.exception;

import com.jm.portfolio.global.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException (String target) {
        super(target + " is not found!");
    }
}
