CREATE DATABASE IF NOT EXISTS market;
USE market;

create table products (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    category varchar(60) not null,
    price varchar(20) not null,

    primary key(id)
);

CREATE USER IF NOT EXISTS  'boss'@'%' IDENTIFIED BY '123456789';
GRANT ALL PRIVILEGES ON *market* TO 'boss'@'%';
FLUSH PRIVILEGES;