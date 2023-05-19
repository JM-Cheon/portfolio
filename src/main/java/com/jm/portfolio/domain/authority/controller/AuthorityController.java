package com.jm.portfolio.domain.authority.controller;

import com.jm.portfolio.domain.authority.dto.request.AuthoritySaveRequest;
import com.jm.portfolio.domain.authority.service.AuthorityService;
import com.jm.portfolio.global.common.response.StatusResponse;
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
    public ResponseEntity<StatusResponse> saveAuth (@RequestBody @Valid final AuthoritySaveRequest newAuth) {
        authorityService.saveAuth(newAuth);
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.CREATED, "success"));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<StatusResponse> getAuthList () {
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success", authorityService.getAuthList()));
    }
}