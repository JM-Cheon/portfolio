package com.jm.portfolio.domain.users.dto.request;

import com.jm.portfolio.domain.model.Birth;
import com.jm.portfolio.domain.model.Email;
import com.jm.portfolio.domain.model.Name;
import com.jm.portfolio.domain.users.domain.Users;
import com.jm.portfolio.global.util.IpUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "회원 정보 DTO")
@Getter
@ToString
@NoArgsConstructor
public class UserDeletionRequest {

    @Schema(description = "이메일")
    private Email email;
    @Schema(description = "비밀번호")
    private String password;

    public Users toEntity() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ip = null;

        if(servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            ip = IpUtil.getClientIp(request);
        }

        return Users.builder()
                .email(email)
                .password(password)
                .withdrawAt(LocalDateTime.now())
                .withdrawIp(ip)
                .isWithdraw(true)
                .build();
    }
}
