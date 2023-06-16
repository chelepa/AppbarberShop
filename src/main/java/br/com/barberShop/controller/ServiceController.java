package br.com.barberShop.controller;

import br.com.barberShop.dto.service.ServiceRequestDTO;
import br.com.barberShop.dto.service.ServiceResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/v1/service/{id}")
    public ResponseEntity<ServiceResponseDTO> searchServiceById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(appService.searchService(id));
    }

    @GetMapping(value = "/v1/service")
    public ResponseEntity<List<ServiceResponseDTO>> getAllService(){
        return ResponseEntity.status(HttpStatus.OK).body(appService.getAllService());
    }

    @PostMapping(value = "/v1/service")
    public ResponseEntity<ServiceResponseDTO> createService(@RequestBody ServiceRequestDTO service){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createService(service));
    }

    @DeleteMapping(value = "/v1/service/{id}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable("id") Integer id){
        appService.deleteServiceById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/v1/service/{id}")
    public ResponseEntity<ServiceResponseDTO> updateServiceById(@PathVariable("id") Integer id, @RequestBody ServiceRequestDTO service){
        service.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(appService.updateService(service));
    }
}
