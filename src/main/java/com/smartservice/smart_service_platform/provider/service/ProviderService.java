package com.smartservice.smart_service_platform.provider.service;

import com.smartservice.smart_service_platform.provider.model.ProviderRequest;
import com.smartservice.smart_service_platform.provider.model.RequestStatus;
import com.smartservice.smart_service_platform.provider.repo.ProviderRequestRepo;
import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import com.smartservice.smart_service_platform.servicecatalog.model.ServiceStatus;
import com.smartservice.smart_service_platform.user.model.Role;
import com.smartservice.smart_service_platform.user.model.User;
import com.smartservice.smart_service_platform.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

    @Autowired
    ProviderRequestRepo repo;

    @Autowired
    UserRepository userRepository;

    public void apply(Authentication authentication, ProviderRequest providerRequest) {
        repo.save(providerRequest);
    }

    public void approve(long id) {
        ProviderRequest request=repo.findById(id).orElseThrow(()->new RuntimeException("Provider Not Found"));
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new RuntimeException("Only PENDING services can be approved");
        }
        request.setStatus(RequestStatus.APPROVED);
        repo.save(request);

        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("Provider Not Found"));
        user.setRole(Role.PROVIDER);
        userRepository.save(user);
    }

    public List<ProviderRequest> getPendingServices(){
        return repo.findByStatus(RequestStatus.PENDING);
    }


    public void reject(long id) {
        ProviderRequest request=repo.findById(id).orElseThrow(()->new RuntimeException("Provider Not Found"));
        request.setStatus(RequestStatus.REJECTED);
        repo.save(request);
    }

    public void disable(long id) {
        ProviderRequest request=repo.findById(id).orElseThrow(()->new RuntimeException("Provider Not Found"));
        request.setStatus(RequestStatus.DISABLED);
        repo.save(request);
    }
}
