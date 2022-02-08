CREATE DATABASE IF NOT EXISTS market;
USE market;

create table product (
	id bigint not null auto_increment,
    name varchar(60) not null,
    category varchar(60) not null,
    price decimal(15,2) not null,
    quantity int(5) not null,

    primary key(id)
);

