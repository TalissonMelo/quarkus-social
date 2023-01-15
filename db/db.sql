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

create table seguidor (
	id bigint not null auto_increment,
	id_usuario bigint not null,
	id_seguidor bigint not null,

	primary key (id)
) engine=InnoDB default charset=utf8;

alter table seguidor add constraint fk_seguidor_usuario
foreign key (id_usuario) references usuario (id);

alter table seguidor add constraint fk_seguidor
foreign key (id_seguidor) references usuario (id);