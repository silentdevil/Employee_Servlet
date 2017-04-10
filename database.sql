
CREATE TABLE employees (
	employeeid int primary key,
	lastname varchar(30),
	firstname varchar(30),
	middlename varchar(30),
	suffix varchar(30),
	title varchar(30),
	address int,
	birthday date,
	gwa float,
	datehired date,
	currentlyhired boolean,
	contact int,
	
	FOREIGN KEY (address) REFERENCES addresses(addressid),
	FOREIGN KEY (contact) REFERENCES contacts(contactid)
);
	
CREATE TABLE addresses (
	addressid int primary key,
	streetno varchar(20),
	street varchar(20),
	brgy varchar(30),
	city varchar(30),
	zipcode varchar(10)
	);
	
CREATE TABLE contacts (
	contactid int primary key,
	landline varchar(20),
	mobile varchar(20),
	email varchar(40)
);

CREATE TABLE employee_role (
	employeeid int,
	roleid int,
	primary key(employeeid,roleid)
);

CREATE TABLE roles (
	roleid int primary key,
	role varchar(20)
);

CREATE SEQUENCE hibernate_sequence;



