package com.jm.portfolio.domain.users.dto;

import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.common.dto.CommonDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Schema(description = "회원 정보 DTO")
public class UsersDTO extends CommonDTO {

    @Schema(description = "회원 번호")
    private long idx;
    @Schema(description = "아메일")
    private String email;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "탈퇴 일자")
    private Date withdrawAt;
    @Schema(description = "탈퇴 IP")
    private String withdrawIp;
    @Schema(description = "탈퇴 여부")
    private String isWithdraw;
    @Schema(description = "계정 활성화 여부")
    private String isDisabled;
    @Schema(description = "계정 만료 여부")
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
