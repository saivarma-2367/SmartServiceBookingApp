package com.smartservice.smart_service_platform.servicecatalog.repo;

import com.smartservice.smart_service_platform.servicecatalog.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<ServiceEntity,Long> {

    public List<ServiceEntity> findByStatus(String status);

    public List<ServiceEntity> findByProviderEmail(String email);
}
