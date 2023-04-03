package com.jm.portfolio.users.controller;

import com.jm.portfolio.users.dto.UsersDTO;
import com.jm.portfolio.users.service.UserCreationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserCreationService userCreationService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp (@RequestBody UsersDTO newUser) {
        userCreationService.signup(newUser);
        return "OK";
    }
}

