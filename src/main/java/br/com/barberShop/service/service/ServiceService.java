package br.com.barberShop.service.service;

import br.com.barberShop.dto.service.ServiceRequestDTO;
import br.com.barberShop.dto.service.ServiceResponseDTO;
import br.com.barberShop.entity.ServiceEntity;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ServiceService extends BaseService {

    @Autowired
    private ModelMapper modelMapper;

    public ServiceResponseDTO createService(ServiceRequestDTO service) {
        log.info("ServiceService.createService - start - ServiceRequestDTO: [{}] ", service);
        var serviceEntity = modelMapper.map(service, ServiceEntity.class);
        var repository = this.saveService(serviceEntity);
        var response = modelMapper.map(repository, ServiceResponseDTO.class);
        log.info("ServiceService.createService - End - ServiceResponseDTO: [{}]", response);
        return response;
    }

    public ServiceResponseDTO searchService(Integer id) {
        log.info("ServiceService.searchService - start - id: [{}] ", id);
        var repository = this.searchServiceById(id);
        var response = modelMapper.map(repository, ServiceResponseDTO.class);
        log.info("ServiceService.searchService - End - id: [{}], ServiceResponseDTO: [{}]", id, response);
        return response;
    }

    public List<ServiceResponseDTO> getAllService() {
        log.info("ServiceService.getAllService - start - ");
        List<ServiceResponseDTO> response = new ArrayList<>();
        var repository = this.searchAllService();
        repository.forEach(service -> response.add(modelMapper.map(service, ServiceResponseDTO.class)));
        log.info("ServiceService.searchService - End - Response: [{}]", response);
        return response;
    }

    public void deleteServiceById(Integer id) {
        log.info("ServiceService.deleteServiceById - start - id: [{}] ", id);
        this.deleteService(id);
        log.info("ServiceService.deleteServiceById - End - id: [{}] - deleted", id);
    }

    public ServiceResponseDTO updateService(ServiceRequestDTO service) {
        log.info("ServiceService.updateService - start - ServiceRequestDTO: [{}] ", service);
        var serviceEntity = this.searchServiceById(service.getId());
        modelMapper.map(service, serviceEntity);
        var repository = this.saveService(serviceEntity);
        var response = modelMapper.map(repository, ServiceResponseDTO.class);
        log.info("ServiceService.updateService - End - ServiceResponseDTO: [{}]", response);
        return response;
    }
}
