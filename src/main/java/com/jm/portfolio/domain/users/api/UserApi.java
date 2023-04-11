package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.dto.request.UserSignupRequest;
import com.jm.portfolio.domain.users.dto.response.UserResponse;
import com.jm.portfolio.domain.users.application.UserCreationService;
import com.jm.portfolio.domain.users.application.UserRetrieveService;
import com.jm.portfolio.global.common.paging.PagingCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name="회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserCreationService userCreationService;
    private final UserRetrieveService userRetrieveService;

    /**
     * 회원 가입 기능
     * @param newUser
     * @return
     */
    @Operation(summary = "회원가입", description = "회원가입 메소드")
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp (@RequestBody UserSignupRequest newUser) {
        userCreationService.signup(newUser);
        return "OK";
    }

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/list")
    public List<UserResponse> getUserList (
//            @Parameter(name = "sort", description = "정렬 기준", example = "createdAt, nickname, email")
            @RequestParam PagingCriteria pagingCriteria) {
        return userRetrieveService.getUserList(pagingCriteria);
    }
}