package com.jm.portfolio.domain.users.dto.response;

import com.jm.portfolio.global.common.base.BaseResponse;
import com.jm.portfolio.domain.users.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 DTO")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse extends BaseResponse {
    @Schema(description = "아메일")
    private String email;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "탈퇴 일자")
    private LocalDateTime withdrawAt;
    @Schema(description = "탈퇴 IP")
    private String withdrawIp;
    @Schema(description = "닉네임")
    private String isWithdraw;
    @Schema(description = "계정 활성화 여부")
    private String isDisabled;
    @Schema(description = "계정 만료 여부")
    private String isExpired;

    public UserResponse(final Users users) {
        super(users.getCreatedAt(), users.getLastUpdatedAt(), users.getCreatedIp(), users.getLastUpdatedIp());
        this.email = users.getEmail();
        this.nickname = users.getNickname();
        this.withdrawAt = users.getWithdrawAt();
        this.withdrawIp = users.getWithdrawIp();
        this.isWithdraw = users.getIsWithdraw();
        this.isDisabled = users.getIsDisabled();
        this.isExpired = users.getIsExpired();
    }
}
