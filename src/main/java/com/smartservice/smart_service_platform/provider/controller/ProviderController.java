package com.smartservice.smart_service_platform.provider.controller;

import com.smartservice.smart_service_platform.provider.model.ProviderRequest;
import com.smartservice.smart_service_platform.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('USER')")
    public String apply(Authentication authentication, @RequestBody ProviderRequest providerRequest){
        providerService.apply(authentication,providerRequest);
        return "Request Submitted Successfully";
    }
}
