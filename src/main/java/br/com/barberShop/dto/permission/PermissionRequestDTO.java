package br.com.barberShop.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDTO {

    private String permission;
    private String description;
}
