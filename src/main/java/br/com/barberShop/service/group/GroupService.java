package br.com.barberShop.service.group;

import br.com.barberShop.dto.group.GroupRequestDTO;
import br.com.barberShop.dto.group.GroupResponseDTO;
import br.com.barberShop.entity.GroupEntity;
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
public class GroupService extends BaseService {

    @Autowired
    private ModelMapper modelMapper;

    public List<GroupEntity> findGroupById(List<Integer> list) {
        log.info("GroupService.findGroupById - Start - List: [{}] ", list);
        List<GroupEntity> groupEntityList = new ArrayList<>();
        list.forEach(id -> groupEntityList.add(this.searchGroupById(id)));
        log.info("GroupService.findGroupById - End - List GroupDTO: [{}]", groupEntityList);
        return groupEntityList;
    }

    public GroupResponseDTO findGroupById(Integer id) {
        log.info("GroupService.findGroupById - Start - id: [{}] ", id);
        GroupEntity repository = this.searchGroupById(id);
        GroupResponseDTO response = modelMapper.map(repository, GroupResponseDTO.class);
        log.info("GroupService.findGroupById - End - id: [{}], GroupDTO: [{}]", id, response);
        return response;
    }

    public List<GroupResponseDTO> searchAllGroups() {
        log.info("GroupService.findGroupById - Start - ");
        List<GroupResponseDTO> listAllGroup = new ArrayList<>();
        var response = this.findAllGroups();
        response.forEach(group -> listAllGroup.add(modelMapper.map(group, GroupResponseDTO.class)));
        log.info("GroupService.findGroupById - End - List GroupDTO: [{}]", listAllGroup);
        return listAllGroup;
    }

    public GroupResponseDTO createGroup(GroupRequestDTO group, List<PermissionEntity> permission) {
        log.info("GroupService.createGroup - start - GroupRequestDTO: [{}] ", group);
        var groupEntity = modelMapper.map(group, GroupEntity.class);
        groupEntity.getPermission().clear();
        groupEntity.getPermission().addAll(permission);
        var repository = this.saveGroup(groupEntity);
        GroupResponseDTO response = modelMapper.map(repository, GroupResponseDTO.class);
        log.info("GroupService.createGroup - End - GroupResponseDTO: [{}] ", response);
        return response;
    }

    public void deleteGroupById(Integer id) {
        log.info("GroupService.deleteGroupById - start - id: [{}] ", id);
        this.deleteGroup(id);
        log.info("GroupService.deleteGroupById - End - id: [{}] - deleted", id);
    }

    public GroupResponseDTO updateGroupById(GroupRequestDTO request, List<PermissionEntity> permission) {
        log.info("GroupService.updateGroupById - start - GroupRequestDTO: [{}]", request);
        var group = this.searchGroupById(request.getId());
        modelMapper.map(request, group);
        group.setPermission((request.getPermission().size() > 0) ? permission : group.getPermission());
        var response = this.saveGroup(group);
        var GroupResponseDTO = modelMapper.map(response, GroupResponseDTO.class);
        log.info("GroupService.updateGroupById - start - GroupResponseDTO: [{}]", GroupResponseDTO);
        return GroupResponseDTO;
    }
}
