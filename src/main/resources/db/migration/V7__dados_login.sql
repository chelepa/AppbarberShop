INSERT INTO tb_users(ds_email, ds_password, ds_name, ds_last_Name, ds_cell)
VALUES ('cl.chelepa@gmail.com', '$2a$10$YYe9VtFGZoWvrNSZNV/AeuVSTOMQLxcGia4IQEl/yVaxrfAnPDcuO', 'claudimir', 'chelepa', '54 991592916');

INSERT INTO tb_group(ds_name, ds_description)VALUES('ADMINISTRADORES', 'ADMINITRADOR');
INSERT INTO tb_group(ds_name, ds_description)VALUES('USUARIOS', 'USUÁRIOS COMUM');

INSERT INTO tb_permission(ds_permission, ds_description) VALUES ('ROLE_ADM', 'ADMINISTRAÇÂO COMPLETA');
INSERT INTO tb_permission(ds_permission, ds_description) VALUES ('ROLE_USR', 'USUÁRIOS');

INSERT INTO tb_permission_group (id_permission, id_group) VALUES ('1', '1');
INSERT INTO tb_permission_group (id_permission, id_group) VALUES ('2', '2');
INSERT INTO tb_users_group (id_users, id_group) VALUES ('1', '1');
