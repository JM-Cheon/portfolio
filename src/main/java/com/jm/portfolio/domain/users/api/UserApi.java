package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.dto.request.UserSignupRequest;
import com.jm.portfolio.domain.users.dto.response.UserSearchResponse;
import com.jm.portfolio.domain.users.service.UsersCreationService;
import com.jm.portfolio.domain.users.service.UsersRetrieveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name="회원 API", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final UsersCreationService usersCreationService;
    private final UsersRetrieveService usersRetrieveService;

    /**
     * 회원 가입 기능
     * @param newUser
     * @return
     */
    @Operation(summary = "회원가입", description = "회원가입 메소드")
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp (@RequestBody UserSignupRequest newUser) {
        usersCreationService.signup(newUser);
        return "OK";
    }

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/list")
    public List<UserSearchResponse> getUserList (
            @Parameter(name = "sort", description = "정렬 기준", example = "idx, createdAt, nickname, email")
            @RequestParam(value = "sort", required = false, defaultValue = "idx") String sort) {
        return usersRetrieveService.getUserList(sort);
    }
}