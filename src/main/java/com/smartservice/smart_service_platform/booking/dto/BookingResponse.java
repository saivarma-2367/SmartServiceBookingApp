package com.smartservice.smart_service_platform.booking.dto;

import com.smartservice.smart_service_platform.booking.model.Booking;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private Long serviceId;
    private String status;
    private LocalDateTime bookedAt;

    public BookingResponse(Booking booking) {
        this.bookingId = booking.getId();
        this.serviceId = booking.getServiceId();
        this.status = booking.getStatus().name();
        this.bookedAt = booking.getBookedAt();
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }
}
