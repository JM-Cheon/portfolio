package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.dto.request.UserSignupRequest;
import com.jm.portfolio.domain.users.application.UserCreationService;
import com.jm.portfolio.domain.users.application.UserRetrieveService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@Tag(name="회원", description = "회원 관련 API")
@RestController
@RequestMapping("/api/v1")
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
    @PostMapping(value = "/user/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> signUp (@RequestBody UserSignupRequest newUser) {
        userCreationService.signup(newUser);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "회원 가입 성공"));
    }

    @Operation(summary = "회원 목록 조회", description = "회원 전체 목록 조회 메소드")
    @GetMapping(value = "/admin/user/list")
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
        response.setData(userRetrieveService.getUserList(criteria));
        response.setPageInfo(new PagingDTO(criteria, userRetrieveService.getUserTotalCount()));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", response));
    }
}