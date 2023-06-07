package br.com.barberShop.dto.customer;

import br.com.barberShop.dto.group.GroupResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String last_name;
    private String cell;
    private String password;
    private List<GroupResponseDTO> group;
}
