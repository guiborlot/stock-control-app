CREATE DATABASE IF NOT EXISTS market;
USE market;

CREATE USER IF NOT EXISTS  'boss'@'%' IDENTIFIED BY '123456789';
GRANT ALL PRIVILEGES ON market.* TO 'boss'@'%';
FLUSH PRIVILEGES;

create table product (
	id bigint not null auto_increment,
    name varchar(60) not null,
    category varchar(60) not null,
    price varchar(20) not null,
    quantity int(5) not null

    primary key(id)
);

