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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
