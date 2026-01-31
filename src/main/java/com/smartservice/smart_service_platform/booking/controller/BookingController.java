package com.smartservice.smart_service_platform.booking.controller;

import com.smartservice.smart_service_platform.booking.dto.BookingResponse;
import com.smartservice.smart_service_platform.booking.dto.CreateBookingRequest;
import com.smartservice.smart_service_platform.booking.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public BookingResponse book(Authentication auth,
                                @RequestBody CreateBookingRequest req) {
        return bookingService.createBooking(auth, req);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<BookingResponse> myBookings(Authentication auth) {
        return bookingService.myBookings(auth);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('USER')")
    public BookingResponse cancel(@PathVariable Long id, Authentication auth) {
        return bookingService.cancelBooking(id, auth);
    }
}

