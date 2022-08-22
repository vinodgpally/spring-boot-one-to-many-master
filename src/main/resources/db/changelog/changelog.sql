create table user_profile (id int not null, first_name varchar(255), last_name varchar(255), username varchar(255), primary key (id));
create table task (id int not null, date_time timestamp, description varchar(255), name varchar(255), status varchar(255) default 'pending', user_id int not null, primary key (id));
insert into  USER_PROFILE(id, first_name, last_name, username) values(1000, 'Ran', 'Suiter', 'rsuit');
insert into  USER_PROFILE(id, first_name, last_name, username) values(1001, 'mary', 'willi', 'maryw');
insert into TASK(id, date_time, description, name, status, user_id) values (10001, sysdate, 'desc', 'task1', 'pending', 1000);
insert into TASK(id, date_time, description, name, status, user_id) values (10002, sysdate, 'desc2', 'task2', 'pending', 1000);