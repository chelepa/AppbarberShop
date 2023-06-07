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
@Table(name = "tb_users")
public class UsersEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_users")
	private Long id;

	@Column(name="ds_name")
	private String name;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_last_Name")
	private String last_name;

	@Column(name = "ds_cell")
	private String cell;

	@Column(name = "ds_password")
	private String password;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "tb_users_group",
			joinColumns = {@JoinColumn(name = "id_users", referencedColumnName = "id_users")},
			inverseJoinColumns = {@JoinColumn(name = "id_group", referencedColumnName = "id_group")}
	)
	private List<GroupEntity> group = new ArrayList<>();
}