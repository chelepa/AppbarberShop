CREATE TABLE tb_permission_group(
   id_permission INT NOT NULL,
   id_group INT NOT NULL,
   CONSTRAINT pk_per_group PRIMARY KEY(id_permission,id_group),
   FOREIGN KEY (id_permission) REFERENCES tb_permission (id_permission),
   FOREIGN KEY (id_group) REFERENCES tb_group (id_group)
);