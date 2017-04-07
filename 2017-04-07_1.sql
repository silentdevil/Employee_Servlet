
CREATE TABLE employees (employeeid int primary key,lastname varchar(30),firstname varchar(30),middlename varchar(30),suffix varchar(30),title varchar(30),address int,birthday date,gwa float,datehired date,currentlyhired boolean,contact int,FOREIGN KEY (address) REFERENCES addresses(addressid),FOREIGN KEY (contact) REFERENCES contacts(contactid));
	
CREATE TABLE addresses (addressid int primary key,streetno varchar(20),street varchar(20),brgy varchar(30),city varchar(30),zipcode varchar(10));
	
CREATE TABLE contacts (contactid int primary key,landline varchar(20),mobile varchar(20),email varchar(40));

CREATE TABLE employee_role (employeeid int,roleid int,primary key(employeeid,roleid));

CREATE TABLE roles (roleid int primary key,role varchar(20));

CREATE SEQUENCE hibernate_sequence;


select * from employees ;
;
select * from employees ;
;
select this_.addressid as addressi1_0_0_, this_.streetno as streetno2_0_0_, this_.street as street3_0_0_, this_.city as city4_0_0_, this_.brgy as brgy5_0_0_, this_.zipcode as zipcode6_0_0_ from addresses this_;
select nextval ('hibernate_sequence');
1 = 4;
insert into addresses (streetno, street, city, brgy, zipcode, addressid) values (1209, 'SUAREZ', 'PASIG', 'MAYBUNGA', '1607', 4);
;
select this_.addressid as addressi1_0_0_, this_.streetno as streetno2_0_0_, this_.street as street3_0_0_, this_.city as city4_0_0_, this_.brgy as brgy5_0_0_, this_.zipcode as zipcode6_0_0_ from addresses this_;
addressi1_0_0_ = 4, streetno2_0_0_ = 1209, street3_0_0_ = SUAREZ, city4_0_0_ = PASIG, brgy5_0_0_ = MAYBUNGA, zipcode6_0_0_ = 1607;
select this_.contactid as contacti1_1_0_, this_.landline as landline2_1_0_, this_.mobile as mobile3_1_0_, this_.email as email4_1_0_ from contacts this_;
select * from employees ;
;
select * from employees ;
;
select this_.addressid as addressi1_0_0_, this_.streetno as streetno2_0_0_, this_.street as street3_0_0_, this_.city as city4_0_0_, this_.brgy as brgy5_0_0_, this_.zipcode as zipcode6_0_0_ from addresses this_;
addressi1_0_0_ = 4, streetno2_0_0_ = 1209, street3_0_0_ = SUAREZ, city4_0_0_ = PASIG, brgy5_0_0_ = MAYBUNGA, zipcode6_0_0_ = 1607;
select this_.contactid as contacti1_1_0_, this_.landline as landline2_1_0_, this_.mobile as mobile3_1_0_, this_.email as email4_1_0_ from contacts this_;
select nextval ('hibernate_sequence');
1 = 5;
insert into contacts (landline, mobile, email, contactid) values ('641-3026', '0932-869-0113', 'jimmikaelcarpio@gmail.com', 5);
;
select this_.contactid as contacti1_1_0_, this_.landline as landline2_1_0_, this_.mobile as mobile3_1_0_, this_.email as email4_1_0_ from contacts this_;
contacti1_1_0_ = 5, landline2_1_0_ = 641-3026, mobile3_1_0_ = 0932-869-0113, email4_1_0_ = jimmikaelcarpio@gmail.com;
select * from roles;
roleid = 1, role = CEO;
roleid = 2, role = DEV;
roleid = 3, role = ECC;
;
select role0_.roleid as roleid1_4_0_, role0_.role as role2_4_0_ from roles role0_ where role0_.roleid=1;
role2_4_0_ = CEO;
;
select nextval ('hibernate_sequence');
1 = 6;
insert into Employees (firstname, lastname, middlename, suffix, title, address, birthday, datehired, gwa, currentlyhired, contact, employeeid) values ('JIM MIKAEL', 'CARPIO', 'ROXAS', '', '', 4, '21-May-96', '23-Jan-17', 1.0, true, 5, 6);
update addresses set streetno=1209, street='SUAREZ', city='PASIG', brgy='MAYBUNGA', zipcode='1607' where addressid=4;
update contacts set landline='641-3026', mobile='0932-869-0113', email='jimmikaelcarpio@gmail.com' where contactid=5;
update roles set role='CEO' where roleid=1;
insert into employee_role (employeeid, roleid) values (6, 1);
;
select * from employees ;
employeeid = 6, firstname = JIM MIKAEL, lastname = CARPIO, middlename = ROXAS, suffix = , title = , address = 4, birthday = 1996-05-21, datehired = 2017-01-23, gwa = 1.0, currentlyhired = true, contact = 5;
;
