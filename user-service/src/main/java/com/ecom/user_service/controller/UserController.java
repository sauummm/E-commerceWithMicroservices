package com.ecom.user_service.controller;


import com.ecom.user_service.dto.AuthResponse;
import com.ecom.user_service.dto.LoginRequest;
import com.ecom.user_service.dto.SignupRequest;
import com.ecom.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(userService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    public ResponseEntity<String> getCurentUser(Authentication authentication){
        return ResponseEntity.ok("Hello, " +authentication.getName());
    }
}
