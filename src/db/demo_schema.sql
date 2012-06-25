-- Copyright 2012 Akuma Huang

-- To create the database:
--   CREATE DATABASE demoapp;
--   GRANT ALL PRIVILEGES ON demoapp.* TO 'demoapp'@'localhost' IDENTIFIED BY 'demoapp';
--
-- To reload the tables:
--   mysql --user=demoapp --password=demoapp --database=demoapp < schema.sql

set session storage_engine = "InnoDB";
set session time_zone = "+8:00";
alter database character set "utf8";

-- 创建用户表
drop table if exists demo_user;
create table demo_user (
    id int auto_increment not null,
    username varchar(16) not null,
    password varchar(20) not null,
    real_name varchar(64) not null,
    birthday datetime null,
    creation_time timestamp not null default 0,
    modify_time timestamp not null on update current_timestamp default current_timestamp,
    constraint pk_user primary key (id)
);

-- 创建用户表的用户名索引
create unique index idx_user_username on demo_user(username);

-- 添加系统管理员用户
insert into demo_user(username, password, real_name, creation_time)
values('admin', '123456', '系统管理员', now());
