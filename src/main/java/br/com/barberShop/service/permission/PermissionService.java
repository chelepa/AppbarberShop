package br.com.barberShop.service.permission;

import br.com.barberShop.dto.permission.PermissionRequestDTO;
import br.com.barberShop.dto.permission.PermissionResponseDTO;
import br.com.barberShop.entity.PermissionEntity;
import br.com.barberShop.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PermissionService extends BaseService {

    @Autowired
    private ModelMapper modelMapper;

    public List<PermissionEntity> findByPermissionList(List<Integer> list) {
        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        list.forEach(id -> permissionEntityList.add(this.searchPermissionId(id)));
        return permissionEntityList;
    }

    public PermissionResponseDTO findPermissionById(Integer id) {
        log.info("PermissionService.findPermissionById - start - id: [{}] ", id);
        var repository = this.searchPermissionId(id);
        var response = modelMapper.map(repository, PermissionResponseDTO.class);
        log.info("PermissionService.findPermissionById - End - id: [{}], PermissionResponseDTO: [{}]", id, response);
        return response;
    }

    public List<PermissionResponseDTO> getAllPermission() {
        log.info("PermissionService.getAllPermission - start ");
        List<PermissionResponseDTO> listPermissionResponse = new ArrayList<>();
        var repository = this.searchAllPermission();
        repository.forEach(permission -> listPermissionResponse.add(modelMapper.map(permission, PermissionResponseDTO.class)));
        log.info("PermissionService.getAllPermission - End - PermissionResponseDTO: [{}]", listPermissionResponse);
        return listPermissionResponse;
    }

    public PermissionResponseDTO createPermission(PermissionRequestDTO permission) {
        log.info("PermissionService.createPermission - start - PermissionRequestDTO: [{}] ", permission);
        var permissionEntity = modelMapper.map(permission, PermissionEntity.class);
        var repository = this.savePermission(permissionEntity);
        var response = modelMapper.map(repository, PermissionResponseDTO.class);
        log.info("PermissionService.createPermission - End - PermissionResponseDTO: [{}] ", response);
        return response;
    }

    public void deletePermissionById(Integer id) {
        log.info("PermissionService.deletePermissionById - start - id: [{}] ", id);
        this.deletePermission(id);
        log.info("PermissionService.deletePermissionById - End - id: [{}] - deleted", id);
    }

    public PermissionResponseDTO updatePermission(PermissionRequestDTO permission) {
        log.info("PermissionService.updatePermission - start - PermissionRequestDTO: [{}]", permission);
        var permissionEntity = this.searchPermissionId(permission.getId());
        modelMapper.map(permission, permissionEntity);
        PermissionEntity repository = this.savePermission(permissionEntity);
        PermissionResponseDTO response = modelMapper.map(repository, PermissionResponseDTO.class);
        log.info("PermissionService.updatePermission - start - PermissionResponseDTO: [{}]", response);
        return response;
    }
}
