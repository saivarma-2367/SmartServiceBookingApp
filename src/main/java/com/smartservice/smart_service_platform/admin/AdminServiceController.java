package com.smartservice.smart_service_platform.admin;

import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import com.smartservice.smart_service_platform.servicecatalog.service.ServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/services")
@PreAuthorize("hasRole('ADMIN')")
public class AdminServiceController {
    private final ServiceService serviceService;

    public AdminServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/pending")
    public List<ServiceResponse> pendingServices() {
        return serviceService.getPendingServices();
    }

    @PutMapping("/{id}/approve")
    public ServiceResponse approve(@PathVariable Long id) {
        return serviceService.approveService(id);
    }

    @PutMapping("/{id}/reject")
    public ServiceResponse reject(@PathVariable Long id) {
        return serviceService.rejectService(id);
    }

    @PutMapping("/{id}/disable")
    public ServiceResponse disable(@PathVariable Long id) {
        return serviceService.disableService(id);
    }
}
