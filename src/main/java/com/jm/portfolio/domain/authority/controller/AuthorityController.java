package com.jm.portfolio.domain.authority.controller;

import com.jm.portfolio.domain.authority.dto.request.AuthoritySaveRequest;
import com.jm.portfolio.domain.authority.service.AuthorityService;
import com.jm.portfolio.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Tag(name="권한", description = "권한 관련 API")
@RestController
@RequestMapping("/api/v1/admin/auth")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse> saveAuth (@RequestBody @Valid final AuthoritySaveRequest newAuth) {
        authorityService.saveAuth(newAuth);
        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.CREATED, "success"));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<ApiResponse> getAuthList () {
        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK, "success", authorityService.getAuthList()));
    }
}
