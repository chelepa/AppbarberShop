package br.com.barberShop.service;

import br.com.barberShop.dto.password.EmailRequestDTO;
import br.com.barberShop.dto.authenticate.AuthenticateRequestDTO;
import br.com.barberShop.dto.authenticate.AuthenticateResponseDTO;
import br.com.barberShop.dto.customer.CustomerDetails;
import br.com.barberShop.dto.customer.CustomerRequestDTO;
import br.com.barberShop.dto.customer.CustomerResponseDTO;
import br.com.barberShop.dto.group.GroupRequestDTO;
import br.com.barberShop.dto.group.GroupResponseDTO;
import br.com.barberShop.dto.password.ResetPasswordRequestDTO;
import br.com.barberShop.dto.password.ResetPasswordResponseDTO;
import br.com.barberShop.dto.permission.PermissionRequestDTO;
import br.com.barberShop.dto.permission.PermissionResponseDTO;
import br.com.barberShop.dto.service.ServiceRequestDTO;
import br.com.barberShop.dto.service.ServiceResponseDTO;
import br.com.barberShop.service.authenticate.AuthenticateSecurityService;
import br.com.barberShop.service.customer.CustomerService;
import br.com.barberShop.service.password.EmailService;
import br.com.barberShop.service.group.GroupService;
import br.com.barberShop.service.password.PasswordResetService;
import br.com.barberShop.service.permission.PermissionService;
import br.com.barberShop.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private AuthenticateSecurityService authenticateSecurityService;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private ServiceService serviceService;

    @Override
    public String sendEmail(HttpServletRequest httpServletRequest, EmailRequestDTO request) {
        return emailService.sendEmail(httpServletRequest, request);
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
    public CustomerDetails customerFindByLogin(String email) {
        return customerService.customerFindByLogin(email);
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

    @Override
    public AuthenticateResponseDTO authenticate(HttpServletRequest request, AuthenticateRequestDTO authenticate) {
        return authenticateSecurityService.authenticate(request, authenticate);
    }

    @Override
    public ModelAndView showChangePasswordPage(String token) {
        return passwordResetService.showChangePasswordPage(token);
    }

    @Override
    public ResetPasswordResponseDTO updatePassword(ResetPasswordRequestDTO request) {
        return passwordResetService.updatePassword(request);
    }

    @Override
    public ServiceResponseDTO searchService(Integer id) {
        return serviceService.searchService(id);
    }

    @Override
    public List<ServiceResponseDTO> getAllService() {
        return serviceService.getAllService();
    }

    @Override
    public ServiceResponseDTO createService(ServiceRequestDTO service) {
        return serviceService.createService(service);
    }

    @Override
    public void deleteServiceById(Integer id) {
        serviceService.deleteServiceById(id);
    }

    @Override
    public ServiceResponseDTO updateService(ServiceRequestDTO service) {
        return serviceService.updateService(service);
    }
}
