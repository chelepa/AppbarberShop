package br.com.barberShop.controller;

import br.com.barberShop.dto.customer.CustomerRequestDTO;
import br.com.barberShop.dto.customer.CustomerResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private AppService appService;

    @PostMapping(value = "/v1/Customer")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createUser(customer));
    }

    @DeleteMapping(value = "/v1/Customer/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") Long id) {
        appService.deleteCustomerById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/v1/Customer/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomerById(@PathVariable("id") Long id, @RequestBody CustomerRequestDTO customer){
        customer.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(appService.updateCustomer(customer));
    }
}
