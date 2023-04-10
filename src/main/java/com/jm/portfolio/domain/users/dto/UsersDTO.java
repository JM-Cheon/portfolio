//package com.jm.portfolio.domain.users.dto;
//
//import com.jm.portfolio.domain.users.domain.Users;
//import com.jm.portfolio.global.common.dto.CommonDTO;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@Schema(description = "회원 정보 DTO")
//public class UsersDTO extends CommonDTO {
//
//    @Schema(description = "회원 번호")
//    private long idx;
//    @Schema(description = "아메일")
//    private String email;
//    @Schema(description = "비밀번호")
//    private String password;
//    @Schema(description = "닉네임")
//    private String nickname;
//    @Schema(description = "탈퇴 일자")
//    private Date withdrawAt;
//    @Schema(description = "탈퇴 IP")
//    private String withdrawIp;
//    @Schema(description = "탈퇴 여부")
//    private String isWithdraw;
//    @Schema(description = "계정 활성화 여부")
//    private String isDisabled;
//    @Schema(description = "계정 만료 여부")
//    private String isExpired;
//
////    @Builder
////    public UsersDTO(Date createdAt, Date lastUpdatedAt, String createdIp, String lastUpdatedIp,
////                    long idx, String email, String password, String nickname, Date withdrawAt, String withdrawIp, String isWithdraw, String isDisabled, String isExpired) {
////        super(createdAt, lastUpdatedAt, createdIp, lastUpdatedIp);
////        this.idx = idx;
////        this.email = email;
////        this.password = password;
////        this.nickname = nickname;
////        this.withdrawAt = withdrawAt;
////        this.withdrawIp = withdrawIp;
////        this.isWithdraw = isWithdraw;
////        this.isDisabled = isDisabled;
////        this.isExpired = isExpired;
////    }
//
////    public Users toEntity() {
////        return Users.builder()
////                .createdAt(getCreatedAt())
////                .lastUpdatedAt(getLastUpdatedAt())
////                .createdIp(getCreatedIp())
////                .lastUpdatedIp(getLastUpdatedIp())
////                .email(email)
////                .password(password)
////                .nickname(nickname)
////                .isWithdraw(isWithdraw)
////                .isDisabled(isDisabled)
////                .isExpired(isExpired)
////                .build();
////    }
//}
