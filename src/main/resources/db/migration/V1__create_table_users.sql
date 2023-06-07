CREATE TABLE tb_users(
   id_users INT NOT NULL AUTO_INCREMENT,
   ds_name VARCHAR (60) NOT NULL,
   ds_last_Name VARCHAR (60) NOT NULL,
   ds_cell VARCHAR (60) NOT NULL,
   ds_email VARCHAR (60) NOT NULL,
   ds_password VARCHAR (400) NOT NULL,
   PRIMARY KEY (id_users)
)