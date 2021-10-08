create table autores(
	id bigint not null auto_increment,	
	nome varchar(60) not null,
	data_nascimento date,
	nacionalidade varchar(100),
	curriculo varchar(500) not null,
	
	primary key(id)
);