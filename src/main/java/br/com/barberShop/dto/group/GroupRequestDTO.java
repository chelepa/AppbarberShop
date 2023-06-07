package br.com.barberShop.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequestDTO {

    private Integer id;
    private String name;
    private String description;
    private List<Integer> permission;

}
