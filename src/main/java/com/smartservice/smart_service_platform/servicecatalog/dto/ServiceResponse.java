package com.smartservice.smart_service_platform.servicecatalog.dto;

import com.smartservice.smart_service_platform.servicecatalog.model.ServiceEntity;

public class ServiceResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String status;

    public ServiceResponse(ServiceEntity service) {
        this.id = service.getId();
        this.name = service.getName();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.status = service.getStatus().name();
    }
}
