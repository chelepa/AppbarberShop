package br.com.barberShop.service.permission;

import br.com.barberShop.entity.PermissionEntity;
import br.com.barberShop.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService extends BaseService {

    public List<PermissionEntity> findByPermissionList(List<Integer> list) {
        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        list.forEach(id -> permissionEntityList.add(this.searchPermissionId(id)));
        return permissionEntityList;
    }
}
