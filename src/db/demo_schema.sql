-- demo 建表 SQL

-- 创建用户表
CREATE TABLE demo_user (
    id INT IDENTITY(1, 1) NOT NULL,
    username VARCHAR(16) NOT NULL,
    password VARCHAR(20) NOT NULL,
    real_name VARCHAR(64) NOT NULL,
    birthday DATETIME NULL,
    modify_time DATETIME DEFAULT getdate() NOT NULL,
    creation_time DATETIME DEFAULT getdate() NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- 创建用户表的用户名索引
CREATE UNIQUE INDEX idx_user_username ON demo_user(username);
