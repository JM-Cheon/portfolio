package com.jm.portfolio.domain.authority.domain;

import com.jm.portfolio.domain.model.AuthorityEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRolePK implements Serializable {

    private Long userIdx;
    private String authorityCode;
}
