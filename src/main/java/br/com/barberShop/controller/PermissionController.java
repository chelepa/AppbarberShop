package br.com.barberShop.controller;

import br.com.barberShop.dto.permission.PermissionRequestDTO;
import br.com.barberShop.dto.permission.PermissionResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PermissionController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/v1/permission/{id}")
    public ResponseEntity<PermissionResponseDTO> searchPermissionId(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findPermissionById(id));
    }

    @GetMapping(value = "/v1/permission")
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermission(){
        return ResponseEntity.status(HttpStatus.OK).body(appService.getAllPermission());
    }

    @PostMapping(value = "/v1/permission")
    public ResponseEntity<PermissionResponseDTO> savePermission(@RequestBody PermissionRequestDTO group) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createPermission(group));
    }

    @DeleteMapping(value = "/v1/permission/{id}")
    public ResponseEntity<Void> deletePermissionId(@PathVariable("id") Integer id) throws Exception {
        appService.deletePermissionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/v1/permission/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable("id") Integer id, @RequestBody PermissionRequestDTO permission){
        return ResponseEntity.status(HttpStatus.OK).body(appService.updatePermissionById(id, permission));
    }
}
