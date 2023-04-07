package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.service.UsersCreationService;
import com.jm.portfolio.domain.users.dto.UsersDTO;
import com.jm.portfolio.domain.users.service.UsersRetrieveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
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
    public String signUp (@RequestBody UsersDTO newUser) {
        usersCreationService.signup(newUser);
        return "OK";
    }

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/list")
    public List<UsersDTO> getUserList (
            @Parameter(name = "sort", description = "정렬 기준", example = "idx, createdAt, nickname, email")
            @RequestParam(value = "sort", required = false, defaultValue = "idx") String sort) {
        return usersRetrieveService.getUserList(sort);
    }
}