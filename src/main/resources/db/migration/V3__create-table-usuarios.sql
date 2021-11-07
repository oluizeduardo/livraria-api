create table usuarios(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	login varchar(100) not null,
	senha varchar(100) not null,
	primary key(id)
);

insert into usuarios (nome, login, senha) values ('ADMIN','@admin', '$2a$10$8JQZcj3zR.V.FqXySO599.taxGcpsXE4Qoo592OI0HaWeegXl1G42');