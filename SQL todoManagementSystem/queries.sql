#spring.datasource.username=root
#spring.datasource.password=Sk123456

create database todo_management;

USE todo_management;

describe todos;

select * from todos;

select bin from todos;


describe users;

describe roles;
Error Code: 1828. Cannot drop column 'role2_id': needed in a foreign key constraint 'FKmvya331lfy3dsvloifgkmg7x4'


describe users_roles;
alter table users_roles drop constraint FKmvya331lfy3dsvloifgkmg7x4;
alter table users_roles drop column role2_id cascade;