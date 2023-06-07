package br.com.barberShop.service;

import br.com.barberShop.dto.EmailRequestDTO;
import br.com.barberShop.dto.customer.CustomerRequestDTO;
import br.com.barberShop.dto.customer.CustomerResponseDTO;
import br.com.barberShop.dto.group.GroupRequestDTO;
import br.com.barberShop.dto.group.GroupResponseDTO;
import br.com.barberShop.dto.permission.PermissionRequestDTO;
import br.com.barberShop.dto.permission.PermissionResponseDTO;

import java.util.List;

public interface AppService {

    String sendEmail(EmailRequestDTO request);

    CustomerResponseDTO createUser(CustomerRequestDTO customer);

    void deleteCustomerById(Long id);

    CustomerResponseDTO updateCustomer(CustomerRequestDTO customer);

    PermissionResponseDTO findPermissionById(Integer id);

    List<PermissionResponseDTO> getAllPermission();

    PermissionResponseDTO createPermission(PermissionRequestDTO permission);

    void deletePermissionById(Integer id);

    PermissionResponseDTO updatePermissionById(PermissionRequestDTO permission);

    GroupResponseDTO findGroupById(Integer id);

    List<GroupResponseDTO> getAllGroup();

    GroupResponseDTO createGroup(GroupRequestDTO group);

    void deleteGroupById(Integer id);

    GroupResponseDTO updateGroupById(GroupRequestDTO group);
}
