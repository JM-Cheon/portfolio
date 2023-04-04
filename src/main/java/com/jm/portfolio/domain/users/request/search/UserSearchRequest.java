package com.jm.portfolio.domain.users.request.search;

import com.jm.portfolio.global.common.dto.CommonDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserSearchRequest extends CommonDTO {

    private long idx;
    private String email;
    private String password;
    private String nickname;
    private Date withdrawAt;
    private String withdrawIp;
    private String isWithdraw;
    private String isDisabled;
    private String isExpired;
}
