package com.smartservice.smart_service_platform.provider.repo;

import com.smartservice.smart_service_platform.provider.model.ProviderRequest;
import com.smartservice.smart_service_platform.provider.model.RequestStatus;
import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRequestRepo extends JpaRepository<ProviderRequest,Long> {

    List<ProviderRequest> findByStatus(RequestStatus status);
}
