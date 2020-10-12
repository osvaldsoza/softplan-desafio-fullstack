CREATE TABLE usuario(
	id serial NOT NULL primary key,
	login varchar(50) not null,
	senha varchar(15) not null,
	tipo varchar(50) not null
);

insert into usuario (login,senha,tipo) values ('Softplan01','admin','ADMINISTRADOR');
insert into usuario (login,senha,tipo) values ('Softplan02','usertriador','USUARIO_TRIADOR');
insert into usuario (login,senha,tipo) values ('Softplan03','userfinalizador','USUARIO_FINALIZADOR');
