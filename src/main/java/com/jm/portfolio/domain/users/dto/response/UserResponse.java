package com.jm.portfolio.domain.users.dto.response;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.global.common.base.dto.response.BaseResponse;
import com.jm.portfolio.domain.users.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "회원 정보 DTO")
@Getter
@ToString
@NoArgsConstructor
public class UserResponse extends BaseResponse {
    @Schema(description = "아메일")
    private Email email;
    @Schema(description = "닉네임")
    private String nickname;
    @Schema(description = "이름")
    private Name name;
    @Schema(description = "생년월일")
    private Birth birth;
    @Schema(description = "탈퇴 일자")
    private LocalDateTime withdrawAt;
    @Schema(description = "탈퇴 IP")
    private String withdrawIp;
    @Schema(description = "탈퇴 여부")
    private boolean isWithdraw;
    @Schema(description = "계정 활성화 여부")
    private boolean isDisabled;
    @Schema(description = "계정 만료 여부")
    private boolean isExpired;

    public UserResponse(final Users users) {
        super(users.getCreatedAt(), users.getLastUpdatedAt(), users.getCreatedIp(), users.getLastUpdatedIp());
        this.email = users.getEmail();
        this.nickname = users.getNickname();
        this.name = users.getName();
        this.birth = users.getBirth();
        this.withdrawAt = users.getWithdrawAt();
        this.withdrawIp = users.getWithdrawIp();
        this.isWithdraw = users.isWithdraw();
        this.isDisabled = users.isDisabled();
        this.isExpired = users.isExpired();
    }
}
