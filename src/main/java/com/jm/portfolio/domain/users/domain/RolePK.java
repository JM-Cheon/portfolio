package com.jm.portfolio.domain.users.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RolePK implements Serializable {

    private long userIdx;
    private String authCode;
}
