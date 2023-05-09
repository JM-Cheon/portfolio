package com.jm.portfolio.domain.admin.controller;

import com.jm.portfolio.domain.admin.service.UserManageService;
import com.jm.portfolio.global.common.paging.SearchCondition;
import com.jm.portfolio.global.common.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@Tag(name="권한", description = "권한 관련 API")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class UserManageController {

    private final UserManageService userManageService;

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/user/list")
    public ResponseEntity<StatusResponse> getUserList (
            @RequestParam(required = false, defaultValue = "1") String offset,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String orderBy,
            @RequestParam(required = false, defaultValue = "nickname") String searchBy,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        SearchCondition searchCondition = new SearchCondition(Integer.parseInt(offset), sortBy, orderBy, searchBy, searchValue, startDate, endDate);

        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", userManageService.getUserList(searchCondition)));
    }

    @Operation(summary = "회원 로그인 이력 조회", description = "회원 로그인 이력 조회 메소드")
    @GetMapping(value = "/sign-in/list")
    public ResponseEntity<StatusResponse> getSingInLog (
            @RequestParam(required = false, defaultValue = "1") String offset,
            @RequestParam(required = false, defaultValue = "idx") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String orderBy,
            @RequestParam(required = false, defaultValue = "email") String searchBy,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        SearchCondition searchCondition = new SearchCondition(Integer.parseInt(offset), sortBy, orderBy, searchBy, searchValue, startDate, endDate);

        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", userManageService.getSignInLog(searchCondition)));
    }
}
