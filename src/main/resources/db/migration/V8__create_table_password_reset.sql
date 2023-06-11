CREATE TABLE tb_password_reset(
   id_password_reset INT NOT NULL AUTO_INCREMENT,
   id_users INT(11) NOT NULL,
   token VARCHAR (255) NOT NULL,
   expiry_date timestamp NOT NULL,
   PRIMARY KEY (id_password_reset, id_users),
   INDEX fk_tb_password_reset_tb_users1_idx (id_users) VISIBLE,
   CONSTRAINT fk_tb_password_reset_tb_users1
     FOREIGN KEY (id_users)
     REFERENCES tb_users (id_users)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION
)