package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.application.AuthService;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Tag(name="권한", description = "권한 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;
    private final CreationService creationService;

    /**
     * 회원 가입 기능
     * @param newUser
     * @return
     */
    @Operation(summary = "회원가입", description = "회원가입 메소드")
    @PostMapping(value = "/signup")
    public ResponseEntity<StatusResponse> signup (@RequestBody @Valid SignupRequest newUser) {
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", creationService.signup(newUser)));
    }

    @Operation(summary = "로그인", description = "로그인 메소드")
    @PostMapping(value = "/signin")
    public ResponseEntity<StatusResponse> signin (@RequestBody @Valid SigninRequest user) {
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success"));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StatusResponse> saveAuth (@RequestBody @Valid final AuthSaveRequest newAuth) {
        authService.saveAuth(newAuth);
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.CREATED, "success"));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<StatusResponse> getAuthList (
            @RequestParam(defaultValue = "1") String offset,
            @RequestParam(defaultValue = "authName") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String orderBy,
            @RequestParam(required = false, defaultValue = "authName") String searchBy,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        Criteria criteria = new Criteria(Integer.parseInt(offset), sortBy, orderBy, searchBy, searchValue, startDate, endDate);

        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", authService.getAuthList(criteria)));
    }
}
