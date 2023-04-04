package com.jm.portfolio.domain.users.api;

import com.jm.portfolio.domain.users.service.UsersCreationService;
import com.jm.portfolio.domain.users.dto.UsersDTO;
import com.jm.portfolio.domain.users.service.UsersRetrieveService;
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
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp (@RequestBody UsersDTO newUser) {
        usersCreationService.signup(newUser);
        return "OK";
    }

    @GetMapping(value = "/list")
    public List<UsersDTO> getUserList (@RequestParam String sort) {
        return usersRetrieveService.getUserList(sort);
    }
}