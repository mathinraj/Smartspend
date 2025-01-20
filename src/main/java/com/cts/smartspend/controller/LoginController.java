package com.cts.smartspend.controller;

import com.cts.smartspend.dto.LoginDTO;
import com.cts.smartspend.service.IUserService;
import com.cts.smartspend.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        return userService.loginUser(loginDTO);
    }
}
