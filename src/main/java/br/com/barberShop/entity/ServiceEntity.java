package br.com.barberShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_service")
public class ServiceEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_service")
    private Long id;

    @Column(name="ds_name")
    private String name;

    @Column(name = "ds_description")
    private String description;

    @Column(name = "vl_service")
    private String value;
}
