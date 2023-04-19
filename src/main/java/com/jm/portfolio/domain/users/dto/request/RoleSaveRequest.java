package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.users.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@NoArgsConstructor
@ToString
public class RoleSaveRequest {

    @Valid
    private long userIdx;
    @Valid
    private String authCode;


    public Role toEntity (){
        return Role.builder()
                .userIdx(userIdx)
                .authCode(authCode)
                .build();
    }
}
