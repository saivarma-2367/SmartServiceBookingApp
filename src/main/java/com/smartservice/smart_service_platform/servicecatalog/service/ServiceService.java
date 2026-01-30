package com.smartservice.smart_service_platform.servicecatalog.service;

import com.smartservice.smart_service_platform.servicecatalog.dto.CreateServiceRequest;
import com.smartservice.smart_service_platform.servicecatalog.dto.ServiceResponse;
import com.smartservice.smart_service_platform.servicecatalog.model.ServiceEntity;
import com.smartservice.smart_service_platform.servicecatalog.model.ServiceStatus;
import com.smartservice.smart_service_platform.servicecatalog.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceRepo repo;

    public ServiceResponse createService(Authentication authentication, CreateServiceRequest createServiceRequest){
        ServiceEntity service=new ServiceEntity();
        service.setProviderEmail(authentication.getName());
        service.setName(createServiceRequest.getName());
        service.setDescription(createServiceRequest.getDescription());
        service.setPrice(createServiceRequest.getPrice());
        service.setStatus(ServiceStatus.PENDING);
        return new ServiceResponse(repo.save(service));
    }

    public List<ServiceResponse> getApprovedServices(){
        List<ServiceEntity> serviceEntities=repo.findByStatus(ServiceStatus.APPROVED);
        List<ServiceResponse> serviceResponses=new ArrayList<>();
        for(ServiceEntity se:serviceEntities){
            ServiceResponse sr=new ServiceResponse(se);
            serviceResponses.add(sr);
        }
        return serviceResponses;
    }

    public List<ServiceResponse> getMyServices(Authentication authentication){
        List<ServiceEntity> serviceEntities=repo.findByProviderEmail(authentication.getName());
        List<ServiceResponse> serviceResponses=new ArrayList<>();
        for(ServiceEntity se:serviceEntities){
            ServiceResponse sr=new ServiceResponse(se);
            serviceResponses.add(sr);
        }return serviceResponses;
    }

    public List<ServiceResponse> getPendingServices() {
        return repo.findByStatus(ServiceStatus.PENDING).stream().map(ServiceResponse::new).toList();
    }

    public ServiceResponse approveService(Long id) {
        ServiceEntity serviceEntity=repo.findById(id).orElseThrow(()->new RuntimeException("Service Not Found"));
        serviceEntity.setStatus(ServiceStatus.APPROVED);
        return new ServiceResponse(repo.save(serviceEntity));
    }

    public ServiceResponse rejectService(Long id) {
        ServiceEntity serviceEntity=repo.findById(id).orElseThrow(()->new RuntimeException("Service Not Found"));
        serviceEntity.setStatus(ServiceStatus.REJECTED);
        return new ServiceResponse(repo.save(serviceEntity));
    }

    public ServiceResponse disableService(Long id) {
        ServiceEntity serviceEntity=repo.findById(id).orElseThrow(()->new RuntimeException("Service Not Found"));
        serviceEntity.setStatus(ServiceStatus.DISABLED);
        return new ServiceResponse(repo.save(serviceEntity));
    }
}
