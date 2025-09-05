package com.ecom.user_service.service;

import com.ecom.user_service.dto.AuthResponse;
import com.ecom.user_service.dto.LoginRequest;
import com.ecom.user_service.dto.SignupRequest;

public interface UserService {
    String signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
}
