package com.jm.portfolio.domain.admin.controller;

import com.jm.portfolio.domain.admin.service.AuthService;
import com.jm.portfolio.domain.admin.dto.request.AuthSaveRequest;
import com.jm.portfolio.global.common.paging.SearchCondition;
import com.jm.portfolio.global.common.response.StatusResponse;
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
@RequestMapping("/api/v1/admin/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
        SearchCondition searchCondition = new SearchCondition(Integer.parseInt(offset), sortBy, orderBy, searchBy, searchValue, startDate, endDate);

        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", authService.getAuthList(searchCondition)));
    }
}
