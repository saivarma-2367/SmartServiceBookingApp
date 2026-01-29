package com.smartservice.smart_service_platform.servicecatalog.controller;

import com.smartservice.smart_service_platform.servicecatalog.dto.CreateServiceRequest;
import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import com.smartservice.smart_service_platform.servicecatalog.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @PostMapping
    @PreAuthorize("hasRole('PROVIDER')")
    public ServiceResponse create(Authentication authentication, @RequestBody CreateServiceRequest createServiceRequest){
        return serviceService.createService(authentication,createServiceRequest);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<ServiceResponse> getAll(){
        return serviceService.getApprovedServices();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('PROVIDER')")
    public List<ServiceResponse> myServices(Authentication authentication){
        return serviceService.getMyServices(authentication);
    }
}
