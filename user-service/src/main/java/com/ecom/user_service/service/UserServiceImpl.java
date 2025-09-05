package com.ecom.user_service.service;

import com.ecom.user_service.config.JwtUtil;
import com.ecom.user_service.dto.AuthResponse;
import com.ecom.user_service.dto.LoginRequest;
import com.ecom.user_service.dto.SignupRequest;
import com.ecom.user_service.dto.UserCreatedEvent;
import com.ecom.user_service.entity.User;
import com.ecom.user_service.producer.UserEventProducer;
import com.ecom.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserEventProducer userEventProducer;



    @Override
    public String signup(SignupRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        User savedUser = userRepository.save(user);

        UserCreatedEvent event = new UserCreatedEvent(
                savedUser.getId(),
                savedUser.getUsername(),
                request.getEmail()
        );
        userEventProducer.sendUserCreatedEvent(event);

        return "User Registered Successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials!");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(token);
    }
}
