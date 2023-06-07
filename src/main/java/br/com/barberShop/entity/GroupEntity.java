package br.com.barberShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_group")
public class GroupEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Integer id;

    @Column(name = "ds_name")
    private String name;

    @Column(name = "ds_description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tb_users_group",
            joinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id_group"),
            inverseJoinColumns = @JoinColumn(name = "id_users", referencedColumnName = "id_users")
    )
    private List<UsersEntity> users = new ArrayList<UsersEntity>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tb_permission_group",
            joinColumns = @JoinColumn(name = "id_group", referencedColumnName = "id_group"),
            inverseJoinColumns = @JoinColumn(name = "id_permission", referencedColumnName = "id_permission")
    )
    private List<PermissionEntity> permission = new ArrayList<>();
}
