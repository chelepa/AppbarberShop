package br.com.barberShop.service.base;

import br.com.barberShop.entity.*;
import br.com.barberShop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    protected UsersEntity createCustomer(UsersEntity usersEntity) {
        return userRepository.saveAndFlush(usersEntity);
    }

    protected UsersEntity searchCustomerById(Long id) {
        Optional<UsersEntity> repository = userRepository.findById(id);
        var response = repository.isPresent() ? repository.get() : Optional.empty();
        return (UsersEntity) response;
    }

    protected UsersEntity searchCustomerByEmail(String email) {
        Optional<UsersEntity> repository = userRepository.findByEmail(email);
        var response = repository.isPresent() ? repository.get() : Optional.empty();
        return (UsersEntity) response;
    }

    protected void deleteCustomer(Long id) {
        var response = this.searchCustomerById(id);
        if (response != null){
            userRepository.delete(response);
        }
    }

    protected GroupEntity searchGroupById(Integer id) {
        var response = (id == null) ? Optional.empty() : groupRepository.findById(id);
        return (GroupEntity) response.orElse(null);
    }

    protected List<GroupEntity> findAllGroups() {
        return groupRepository.findAll();
    }

    protected GroupEntity saveGroup(GroupEntity groupEntity) {
        return groupRepository.saveAndFlush(groupEntity);
    }

    protected void deleteGroup(Integer id) {
        var response = this.searchGroupById(id);
        if (response != null){
            groupRepository.delete(response);
        }
    }

    protected PermissionEntity searchPermissionId(Integer id) {
        var response = (id == null) ? Optional.empty() : permissionRepository.findById(id);
        return (PermissionEntity) response.orElse(null);
    }

    protected List<PermissionEntity> searchAllPermission() {
        return permissionRepository.findAll();
    }

    protected PermissionEntity savePermission(PermissionEntity permissionEntity) {
        return permissionRepository.saveAndFlush(permissionEntity);
    }

    protected void deletePermission(Integer id) {
        var response = this.searchPermissionId(id);
        if (response != null){
            permissionRepository.delete(response);
        }
    }

    protected PasswordResetEntity createPasswordResetTokenForUser(UsersEntity user, String token) {
        PasswordResetEntity response = new PasswordResetEntity();
        response.setToken(token);
        response.setId_users(user);
        response.setExpiryDate(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(10));
        return this.createPasswordReset(response);
    }

    protected PasswordResetEntity createPasswordReset(PasswordResetEntity response) {
        return passwordResetRepository.save(response);
    }

    protected Optional<PasswordResetEntity> getTokenPasswordReset(String token) {
        return passwordResetRepository.findByToken(token);
    }

    protected ServiceEntity saveService(ServiceEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    protected ServiceEntity searchServiceById(Integer id) {
        var repository = serviceRepository.findById(Long.valueOf(id));
        var response = repository.isPresent() ? repository.get() : Optional.empty();
        return (ServiceEntity) response;
    }

    protected List<ServiceEntity> searchAllService() {
        return serviceRepository.findAll();
    }

    protected void deleteService(Integer id) {
        serviceRepository.deleteById(Long.valueOf(id));
    }
}
