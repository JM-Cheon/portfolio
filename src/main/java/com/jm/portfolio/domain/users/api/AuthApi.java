package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.application.AuthService;
import com.jm.portfolio.domain.users.dto.request.AuthSaveRequest;
import com.jm.portfolio.domain.users.dto.response.AuthResponse;
import com.jm.portfolio.global.common.response.StatusResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name="권한", description = "권한 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping(value = "/save")
    public ResponseEntity<StatusResponse> saveAuth (@RequestBody AuthSaveRequest newAuth) {
        authService.saveAuth(newAuth);
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.CREATED, "success"));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<StatusResponse> getAuthList () {
        List<AuthResponse> authList = authService.getAuthList();
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.CREATED, "success"), authList);
    }
}
