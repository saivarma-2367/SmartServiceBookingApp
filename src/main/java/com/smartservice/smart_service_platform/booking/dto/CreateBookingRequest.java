package com.smartservice.smart_service_platform.booking.dto;

public class CreateBookingRequest {
    private Long serviceId;

    public CreateBookingRequest(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
