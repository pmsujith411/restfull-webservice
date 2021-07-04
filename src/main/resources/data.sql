drop table if exists user;

create table user (
	id integer not null, 
	birth_date timestamp, 
	name varchar(255), 
	primary key (id)
);

insert into user values(1, sysdate(), 'qwe');
insert into user values(2, sysdate(), 'ert');
insert into user values(3, sysdate(), 'tyu');
insert into user values(4, sysdate(), 'uio');