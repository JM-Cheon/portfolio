package com.jm.portfolio.domain.users.dto;

import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.common.dto.CommonDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class UsersDTO extends CommonDTO {

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

    public UsersDTO (Users users) {
        this.idx = users.getIdx();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.nickname = users.getNickname();
        this.withdrawAt = users.getWithdrawAt();
        this.withdrawIp = users.getWithdrawIp();
        this.isWithdraw = users.getIsWithdraw();
        this.isDisabled = users.getIsDisabled();
        this.isExpired = users.getIsExpired();
        this.setCreatedAt(users.getCreatedAt());
        this.setCreatedIp(users.getCreatedIp());
        this.setLastUpdatedAt(users.getLastUpdatedAt());
        this.setLastUpdatedIp(users.getLastUpdatedIp());
    }

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
