package com.jm.portfolio.domain.notice.controller;

import com.jm.portfolio.global.common.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name="공지 사항", description = "공지 사항 관련 API")
@RestController
@RequestMapping("/api/v1/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    @Operation(summary = "공지 사항 등록", description = "공지 사항 등록 메소드")
    @PostMapping(value = {"/", ""})
    public ResponseEntity<StatusResponse> createNotice() {
        return ResponseEntity.ok().body(new StatusResponse(HttpStatus.OK, "success"));
    }
}
