package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.application.RetrieveService;
import com.jm.portfolio.global.common.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Tag(name="회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final RetrieveService retrieveService;
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

    @Operation(summary = "회원 본인의 정보", description = "회원 본인의 정보를 조회하는 메소드")
    @GetMapping(value = "/my")
    public ResponseEntity<StatusResponse> myInfo (@RequestParam @Valid String email) {
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", retrieveService.myInfo(email)));
    }
}