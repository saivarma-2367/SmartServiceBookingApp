package com.smartservice.smart_service_platform;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Authentication authentication) {
        System.out.println(authentication.getClass());
        return "OK";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/profile")
    public String profile() {
        return "User profile";
    }


}
