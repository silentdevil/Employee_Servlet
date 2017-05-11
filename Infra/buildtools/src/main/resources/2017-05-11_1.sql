
CREATE USER jim WITH PASSWORD 'ex1stgl0bal';
 
DROP DATABASE IF EXISTS mydatabase;

CREATE DATABASE mydatabase OWNER jim;

\c mydatabase;

drop schema public cascade;
create schema public;

CREATE SEQUENCE hibernate_sequence;
CREATE TABLE employees (
	employee_id serial primary key,

	last_name varchar(30),
	first_name varchar(30),
	middle_name varchar(30),
	suffix varchar(30),
	title varchar(30),

	street_no int,
	street varchar(20),
	brgy varchar(30),
	city varchar(30),
	zipcode varchar(10),

	birthday date,
	gwa float,
	date_hired date,
	currently_hired boolean
);
	
CREATE TABLE contacts (
	contact_id serial,
	employee_id int,
	contact_type varchar(60),
	contact_info varchar(60),
	constraint fk_employee_id foreign key(employee_id) references employees(employee_id) 
	on delete cascade
);

CREATE TABLE roles (
	role_id serial primary key,
	role varchar(20)
);

CREATE TABLE employee_role (
	employee_id int,
	role_id int,
	primary key(employee_id,role_id),
	constraint fk_employee_id foreign key(employee_id) references employees(employee_id),
	constraint fk_role_id foreign key(role_id) references roles(role_id)
);

GRANT  USAGE   ON SCHEMA public  TO jim;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public 
TO jim;

GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO jim;




INSERT INTO roles(role) VALUES ('CEO');
INSERT INTO roles(role) VALUES ('VICE PRESIDENT');
INSERT INTO roles(role) VALUES ('DEVELOPER');
INSERT INTO roles(role) VALUES ('DESIGNER');
INSERT INTO roles(role) VALUES ('BA');



INSERT INTO EMPLOYEES(last_name, first_name, middle_name, suffix, title, street_no,street,brgy,city,zipcode, birthday, gwa, date_hired, currently_hired)
	VALUES('CARPIO','JIM MIKAEL','ROXAS','','','1209','SUAREZ','MAYBUNGA','PASIG','1607','1996-05-21',4,'2017-01-23','T');

INSERT INTO CONTACTS(employee_id, contact_type, contact_info) VALUES (1,'EMAIL','jimmikael@gmail.com');

INSERT INTO employee_role(employee_id, role_id) VALUES (1,1);




