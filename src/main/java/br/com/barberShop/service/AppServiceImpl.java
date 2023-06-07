package br.com.barberShop.service;

import br.com.barberShop.dto.EmailRequestDTO;
import br.com.barberShop.dto.customer.CustomerRequestDTO;
import br.com.barberShop.dto.customer.CustomerResponseDTO;
import br.com.barberShop.dto.group.GroupRequestDTO;
import br.com.barberShop.dto.group.GroupResponseDTO;
import br.com.barberShop.dto.permission.PermissionRequestDTO;
import br.com.barberShop.dto.permission.PermissionResponseDTO;
import br.com.barberShop.service.customer.CustomerService;
import br.com.barberShop.service.email.EmailService;
import br.com.barberShop.service.group.GroupService;
import br.com.barberShop.service.permission.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public String sendEmail(EmailRequestDTO request) {
        return emailService.sendEmail(request);
    }

    @Override
    public CustomerResponseDTO createUser(CustomerRequestDTO customer) {
        var groupList = groupService.findGroupById(customer.getGroup());
        return customerService.createCustomer(customer, groupList);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerService.deleteCustomerById(id);
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerRequestDTO customer) {
        var groupList = groupService.findGroupById(customer.getGroup());
        return customerService.updateCustomer(customer, groupList);
    }

    @Override
    public PermissionResponseDTO findPermissionById(Integer id) {
        return permissionService.findPermissionById(id);
    }

    @Override
    public List<PermissionResponseDTO> getAllPermission() {
        return permissionService.getAllPermission();
    }

    @Override
    public PermissionResponseDTO createPermission(PermissionRequestDTO permission) {
        return permissionService.createPermission(permission);
    }

    @Override
    public void deletePermissionById(Integer id) {
        permissionService.deletePermissionById(id);
    }

    @Override
    public PermissionResponseDTO updatePermissionById(PermissionRequestDTO permission) {
        return permissionService.updatePermission(permission);
    }

    @Override
    public GroupResponseDTO findGroupById(Integer id) {
        return groupService.findGroupById(id);
    }

    @Override
    public List<GroupResponseDTO> getAllGroup() {
        return groupService.searchAllGroups();
    }

    @Override
    public GroupResponseDTO createGroup(GroupRequestDTO group) {
        var permission = permissionService.findByPermissionList(group.getPermission());
        return groupService.createGroup(group, permission);
    }

    @Override
    public void deleteGroupById(Integer id) {
        groupService.deleteGroupById(id);
    }

    @Override
    public GroupResponseDTO updateGroupById(GroupRequestDTO group) {
        var permission = permissionService.findByPermissionList(group.getPermission());
        return groupService.updateGroupById(group, permission);
    }
}
