package com.smartservice.smart_service_platform.booking.repo;

import com.smartservice.smart_service_platform.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    List<Booking> findByUserEmail(String userEmail);

    List<Booking> findByProviderEmail(String providerEmail);
}
