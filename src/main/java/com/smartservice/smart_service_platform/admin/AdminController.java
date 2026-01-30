package com.smartservice.smart_service_platform.admin;

import com.smartservice.smart_service_platform.provider.model.ProviderRequest;
import com.smartservice.smart_service_platform.provider.service.ProviderService;
import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    ProviderService service;

    @PutMapping("/providers/{id}/approve")
    public String approve(@PathVariable long id){
        service.approve(id);
        return "Provider Approved";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Admin Dashboard Accessed";
    }

    @GetMapping("/pending")
    public List<ProviderRequest> pendingServices(){
        return service.getPendingServices();
    }

    @PutMapping("/providers/{id}/reject")
    public String reject(@PathVariable long id){
        service.reject(id);
        return "Provider Rejected";
    }

    @PutMapping("/providers/{id}/disable")
    public String diable(@PathVariable long id){
        service.disable(id);
        return "Provider Disabled";
    }


}
