package br.com.barberShop.dto.group;

import br.com.barberShop.dto.permission.PermissionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private List<PermissionResponseDTO> permission;
}
