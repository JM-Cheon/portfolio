package com.jm.portfolio.domain.users.dto.response;

import com.jm.portfolio.domain.common.dto.response.BaseResponse;
import com.jm.portfolio.domain.users.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(description = "회원 정보 DTO")
public class UserSearchResponse extends BaseResponse {

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

    public UserSearchResponse (Users users) {
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
}
