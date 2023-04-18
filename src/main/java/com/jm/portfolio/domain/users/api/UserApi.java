package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.dto.request.SigninRequest;
import com.jm.portfolio.domain.users.dto.request.SignupRequest;
import com.jm.portfolio.domain.users.application.CreationService;
import com.jm.portfolio.domain.users.application.RetrieveService;
import com.jm.portfolio.global.common.paging.dto.Criteria;
import com.jm.portfolio.global.common.paging.dto.PagingDTO;
import com.jm.portfolio.global.common.paging.dto.response.PagingResponse;
import com.jm.portfolio.global.common.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Tag(name="회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final CreationService creationService;
    private final RetrieveService retrieveService;

    /**
     * 회원 가입 기능
     * @param newUser
     * @return
     */
    @Operation(summary = "회원가입", description = "회원가입 메소드")
    @PostMapping(value = "/signup")
    public ResponseEntity<ResponseDTO> signup (@RequestBody SignupRequest newUser) {
        creationService.signup(newUser);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 가입 성공"));
    }

    @Operation(summary = "로그인", description = "로그인 메소드")
    @PostMapping(value = "/signin")
    public ResponseEntity<ResponseDTO> signin (@RequestBody SigninRequest user) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "로그인 성공"));
    }

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/list")
    public ResponseEntity<ResponseDTO> getUserList (
            @RequestParam(required = false, defaultValue = "1") String offset,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String orderBy,
            @RequestParam(required = false, defaultValue = "nickname") String searchBy,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        Criteria criteria = new Criteria(Integer.parseInt(offset), sortBy, orderBy, searchBy, searchValue, startDate, endDate);

        PagingResponse response = new PagingResponse();
        response.setData(retrieveService.getUserList(criteria));
        response.setPageInfo(new PagingDTO(criteria, retrieveService.getUserTotalCount()));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", response));
    }
}