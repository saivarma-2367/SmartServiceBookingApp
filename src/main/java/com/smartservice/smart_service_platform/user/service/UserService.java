package com.smartservice.smart_service_platform.user.service;

import com.smartservice.smart_service_platform.user.dto.UpdateUserRequest;
import com.smartservice.smart_service_platform.user.dto.UserResponse;
import com.smartservice.smart_service_platform.user.model.User;
import com.smartservice.smart_service_platform.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse getCurrentUser(Authentication authentication){
        String email=authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(user.getId(),user.getName(), user.getEmail(), user.getRole().name());
    }

    public UserResponse updateCurrentUser(Authentication authentication,@RequestBody UpdateUserRequest updateUserRequest){
        String email=authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updateUserRequest.getName());
        userRepository.save(user);

        return new UserResponse(user.getId(),user.getName(), user.getEmail(), user.getRole().name());
    }
}
