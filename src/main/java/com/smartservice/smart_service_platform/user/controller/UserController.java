package com.smartservice.smart_service_platform.user.controller;

import com.smartservice.smart_service_platform.user.dto.UpdateUserRequest;
import com.smartservice.smart_service_platform.user.dto.UserResponse;
import com.smartservice.smart_service_platform.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserResponse getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.getCurrentUser(authentication);
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserResponse updateCurrentUser(UpdateUserRequest updateUserRequest){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return userService.updateCurrentUser(authentication,updateUserRequest);
    }
}
