package com.jm.portfolio.users.dto;

import com.jm.portfolio.common.dto.BaseDTO;
import com.jm.portfolio.users.entity.Users;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class UsersDTO extends BaseDTO {

    private long idx;
    private String email;
    private String password;
    private String nickname;
    private Date withdrawAt;
    private String withdrawIp;
    private String isWithdraw;
    private String isDisabled;
    private String isExpired;

//    @Builder
//    public UsersDTO(String email, String password, String nickname, Date createdAt, Date lastUpdatedAt, String createdIp, String lastUpdatedIp) {
//        super(createdAt, lastUpdatedAt, createdIp, lastUpdatedIp);
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//    }

    public Users toEntity() {
        return Users.builder()
                .createdAt(getCreatedAt())
                .lastUpdatedAt(getLastUpdatedAt())
                .createdIp(getCreatedIp())
                .lastUpdatedIp(getLastUpdatedIp())
                .email(email)
                .password(password)
                .nickname(nickname)
                .isWithdraw(isWithdraw)
                .isDisabled(isDisabled)
                .isExpired(isExpired)
                .build();
    }
}
