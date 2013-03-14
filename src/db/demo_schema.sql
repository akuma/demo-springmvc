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
    id int unsigned auto_increment not null,
    username varchar(20) not null, -- 用户名
    password varchar(64) not null, -- 密码
    real_name varchar(64) not null, -- 真实姓名
    birthday date null, -- 生日
    creation_time timestamp not null default 0,
    modify_time timestamp not null on update current_timestamp default current_timestamp,
    constraint pk_user primary key (id)
)
autoincrement 1000 comment '用户表';

-- 创建用户表的用户名索引
create unique index idx_user_username on demo_user(username);

-- 添加系统管理员用户
insert into demo_user(username, password, real_name, creation_time)
values('admin', '123456', '系统管理员', now());

-- 创建用户组表
drop table if exists demo_group;
create table demo_group (
    id int unsigned auto_increment not null,
    group_name varchar(20) not null comment '用户组名',
    is_enable boolean null default 1 comment '是否启用',
    description varchar(100) null comment '描述信息',
    creation_time timestamp not null default 0,
    modify_time timestamp not null on update current_timestamp default current_timestamp,
    constraint pk_demo_group primary key (id)
)
auto_increment 1000 comment '用户组表';

-- 创建用户组关联表
drop table if exists group_user;
create table group_user (
    group_id int unsigned not null,
    user_id int unsigned not null,
    constraint pk_group_user primary key (group_id, user_id)
)
comment '用户组关联表';

-- 创建用户 ID 索引
create index idx_group_user_uid on group_user(user_id);
