create database quarkus_social;

use quarkus_social;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	idade bigint not null,

	primary key (id)
) engine=InnoDB default charset=utf8;