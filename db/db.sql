create database quarkus_social;

use quarkus_social;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	idade bigint not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

create table postagem (
	id bigint not null auto_increment,
	nome varchar(250) not null,
	data datetime not null,
	id_usuario bigint not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

alter table postagem add constraint fk_usuario
foreign key (id_usuario) references usuario (id);