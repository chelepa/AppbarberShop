CREATE TABLE tb_users_group(
  id_users INT NOT NULL,
  id_group   INT NOT NULL,
  CONSTRAINT pk_usu_group PRIMARY KEY(id_users,id_group),
  FOREIGN KEY (id_users)  REFERENCES tb_users(id_users),
  FOREIGN KEY (id_group)  REFERENCES tb_group(id_group)
);