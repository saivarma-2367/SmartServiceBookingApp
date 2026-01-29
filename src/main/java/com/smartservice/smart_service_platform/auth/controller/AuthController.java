package com.smartservice.smart_service_platform.auth.controller;

import com.smartservice.smart_service_platform.auth.dto.LoginRequest;
import com.smartservice.smart_service_platform.auth.dto.LoginResponse;
import com.smartservice.smart_service_platform.auth.dto.RegisterRequest;
import com.smartservice.smart_service_platform.common.config.JWTUtil;
import com.smartservice.smart_service_platform.user.model.Role;
import com.smartservice.smart_service_platform.user.model.User;
import com.smartservice.smart_service_platform.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        User user=new User();
        user.setEmail(registerRequest.email());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setName(registerRequest.name());
        user.setRole(Role.USER);
        userRepository.save(user);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        User user=userRepository.findByEmail(loginRequest.email()).orElseThrow(()->new RuntimeException("User Credentials Not found"));

        if(!passwordEncoder.matches(loginRequest.password(),user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String token= jwtUtil.generateToken(loginRequest.email(), user.getRole().name());
        System.out.println("GENERATED TOKEN: " + token);
        return ResponseEntity.ok(new LoginResponse(token));

    }
}
