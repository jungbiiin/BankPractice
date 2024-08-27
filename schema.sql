create schema db_bank;

use db_bank;

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
    balance    int not null
);

create table tb_account_history
(
    account_history_id int not null primary key auto_increment,
    account_id         int not null,
    amount             int not null,
    create_datetime    int not null
);