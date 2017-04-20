drop sequence laf_data_seq;
drop table laf_data;
drop table laf_user;

create table laf_user(
phone varchar(11),
password varchar2(255),
name varchar2(100) constraint laf_user_name_nn not null,
sex char(6),
address varchar2(255),
constraint laf_user_sex_ck check(sex in('ÄÐ','Å®')),
constraint laf_user_phone_pk primary key(phone));


create table laf_data(
id number(10),
type char(4) constraint laf_data_type_nn not null,
name varchar2(100) constraint laf_data_name_nn not null,
detail varchar2(255),
remark varchar2(255),
place varchar2(255),
incident_date1 date,
incident_time1 varchar(10),
incident_date2 date,
incident_time2 varchar(10),
publish_date date,
publish_time varchar(10),
isfinish char(1),
pic_name varchar2(20),
user_phone varchar(11) constraint laf_data_user_phone_nn not null,
constraint laf_data_type_ck check(type in('lost','find','peop')),
constraint laf_data_isfinish_ck check(isfinish in('y','n')),
constraint laf_data_user_phone_fk foreign key(user_phone) references laf_user(phone),
constraint laf_data_id_pk primary key(id));

create sequence laf_data_seq start with 1 increment by 1;