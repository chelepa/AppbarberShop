package br.com.barberShop.controller;

import br.com.barberShop.dto.group.GroupRequestDTO;
import br.com.barberShop.dto.group.GroupResponseDTO;
import br.com.barberShop.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/v1/group/{id}")
    public ResponseEntity<GroupResponseDTO> searchGroupById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(appService.findGroupById(id));
    }

    @GetMapping(value = "/v1/group")
    public ResponseEntity<List<GroupResponseDTO>> getAllGroup(){
        return ResponseEntity.status(HttpStatus.OK).body(appService.getAllGroup());
    }

    @PostMapping(value = "/v1/group")
    public ResponseEntity<GroupResponseDTO> createGroup(@RequestBody GroupRequestDTO group){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createGroup(group));
    }

    @DeleteMapping(value = "/v1/group/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable("id") Integer id){
        appService.deleteGroupById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/v1/group/{id}")
    public ResponseEntity<GroupResponseDTO> updateGroupById(@PathVariable("id") Integer id, @RequestBody GroupRequestDTO group){
        group.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(appService.updateGroupById(group));
    }
}
