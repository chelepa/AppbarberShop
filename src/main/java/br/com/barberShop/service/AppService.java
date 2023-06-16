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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AppService {

    String sendEmail(HttpServletRequest httpServletRequest, EmailRequestDTO request);

    CustomerResponseDTO createUser(CustomerRequestDTO customer);

    void deleteCustomerById(Long id);

    CustomerResponseDTO updateCustomer(CustomerRequestDTO customer);

    CustomerDetails customerFindByLogin(String email);

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

    AuthenticateResponseDTO authenticate(HttpServletRequest request, AuthenticateRequestDTO authenticate);

    ModelAndView showChangePasswordPage(String token);

    ResetPasswordResponseDTO updatePassword(ResetPasswordRequestDTO request);

    ServiceResponseDTO searchService(Integer id);

    List<ServiceResponseDTO> getAllService();

    ServiceResponseDTO createService(ServiceRequestDTO service);

    void deleteServiceById(Integer id);

    ServiceResponseDTO updateService(ServiceRequestDTO service);
}
