CREATE SCHEMA IF NOT EXISTS db_bank;
USE db_bank;

DROP TABLE IF EXISTS tb_account_history;
DROP TABLE IF EXISTS tb_account;
DROP TABLE IF EXISTS tb_user;

create table tb_user
(
    user_id       int         not null primary key auto_increment,
    user_name     varchar(50) not null,
    user_password varchar(50) not null
);


create table tb_account
(
    account_id int not null primary key auto_increment,
    user_id    int not null,
    balance    int not null,
    currency varchar(10) not null
);

create table tb_account_history
(
    account_history_id int not null primary key auto_increment,
    account_id         int not null,
    amount             int not null,
    difference         int not null,
    create_datetime    DATETIME not null,
    transaction_account_id int
);