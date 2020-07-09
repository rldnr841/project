create table tbl_member (
email varchar2(100),
pwd varchar2(1000) not null,
nickname varchar2(100) not null,
regd8 date default sysdate);

alter table tbl_member add constraint pk_member
primary key(email);