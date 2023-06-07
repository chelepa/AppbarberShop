package br.com.barberShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_permission")
public class PermissionEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_permission")
    private Integer id;

    @Column(name="ds_permission")
    private String permission;

    @Column(name="ds_description")
    private String description;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name="tb_permission_group",
//            joinColumns=@JoinColumn(name="id_permission", referencedColumnName="id_permission"),
//            inverseJoinColumns=@JoinColumn(name="id_group", referencedColumnName="id_group")
//    )
//    private List<GroupEntity> group = new ArrayList<>();
}
