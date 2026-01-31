package com.smartservice.smart_service_platform.booking.service;

import com.smartservice.smart_service_platform.booking.dto.BookingResponse;
import com.smartservice.smart_service_platform.booking.dto.CreateBookingRequest;
import com.smartservice.smart_service_platform.booking.model.Booking;
import com.smartservice.smart_service_platform.booking.model.BookingStatus;
import com.smartservice.smart_service_platform.booking.repo.BookingRepo;
import com.smartservice.smart_service_platform.servicecatalog.model.ServiceEntity;
import com.smartservice.smart_service_platform.servicecatalog.model.ServiceStatus;
import com.smartservice.smart_service_platform.servicecatalog.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepo bookingRepo;
    @Autowired
    ServiceRepo serviceRepo;

    public BookingResponse createBooking(Authentication authentication, CreateBookingRequest createBookingRequest){
        ServiceEntity serviceEntity=serviceRepo.findById(createBookingRequest.getServiceId()).orElseThrow(()->new RuntimeException("Service Not Found"));

        if (serviceEntity.getStatus() != ServiceStatus.APPROVED) {
            throw new RuntimeException("Service not available for booking");
        }

        Booking booking=new Booking();
        booking.setServiceId(serviceEntity.getId());
        booking.setUserEmail(authentication.getName());
        booking.setProviderEmail(serviceEntity.getProviderEmail());
        booking.setStatus(BookingStatus.CREATED);
        booking.setBookedAt(LocalDateTime.now());

        return new BookingResponse(bookingRepo.save(booking));
    }

    public List<BookingResponse> myBookings(Authentication authentication){
        return bookingRepo.findByUserEmail(authentication.getName()).stream().map(BookingResponse::new).toList();
    }

    public BookingResponse cancelBooking(Long id, Authentication auth) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUserEmail().equals(auth.getName())) {
            throw new RuntimeException("Not your booking");
        }

        if (booking.getStatus() != BookingStatus.CREATED) {
            throw new RuntimeException("Booking cannot be cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return new BookingResponse(bookingRepo.save(booking));
    }
}
