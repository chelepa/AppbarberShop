package br.com.barberShop.service.customer;

import br.com.barberShop.dto.customer.CustomerDetails;
import br.com.barberShop.dto.customer.CustomerRequestDTO;
import br.com.barberShop.dto.customer.CustomerResponseDTO;
import br.com.barberShop.entity.GroupEntity;
import br.com.barberShop.entity.UsersEntity;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService extends BaseService {

    @Autowired
    private ModelMapper modelMapper;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public CustomerResponseDTO createCustomer(CustomerRequestDTO customer, List<GroupEntity> groupList) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.getGroup().clear();
        log.info("CustomerService.createCustomer - Start - Customer: [{}] ", customer);
        var usersEntity = modelMapper.map(customer, UsersEntity.class);
        usersEntity.getGroup().addAll(groupList);
        var repository = this.createCustomer(usersEntity);
        var response = modelMapper.map(repository, CustomerResponseDTO.class);
        log.info("CustomerService.createCustomer - End - Customer: [{}] ", response);
        return response;
    }

    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customer, List<GroupEntity> groupList) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        log.info("CustomerService.updateCustomer - start - Customer: [{}]", customer);
        UsersEntity repository = this.searchCustomerById(customer.getId());
        modelMapper.map(customer, repository);
        repository.setGroup((customer.getGroup().size() > 0) ? groupList : repository.getGroup());
        UsersEntity user = this.createCustomer(repository);
        CustomerResponseDTO response = modelMapper.map(user, CustomerResponseDTO.class);
        log.info("CustomerService.updateCustomer - End - CustomerResponse: [{}]", response);
        return response;
    }

    public void deleteCustomerById(Long id) {
        log.info("CustomerService.deleteCustomerById - Start - Id: [{}] ", id);
        this.deleteCustomer(id);
        log.info("CustomerService.deleteCustomerById - End - Id: [{}] - Deleted", id);
    }

    public CustomerDetails customerFindByLogin(String email) {
        log.info("CustomerService.customerFindByLogin - Start - Email: [{}] ", email);
        UsersEntity userResponse = this.searchCustomerByEmail(email);
        CustomerDetails response = modelMapper.map(userResponse, CustomerDetails.class);
        log.info("CustomerService.customerFindByLogin - End - Email: [{}], Response: [{}] ", email, response);
        return response;
    }
}
