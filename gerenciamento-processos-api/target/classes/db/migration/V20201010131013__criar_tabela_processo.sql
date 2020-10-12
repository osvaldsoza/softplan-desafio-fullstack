CREATE TABLE processo(
	id serial NOT NULL primary key,
	numero varchar(50) not null,
	data_entrada timestamp NOT null,
	data_baixa timestamp,
	parecer varchar(20) NOT NULL,
	id_usuario bigint not NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

insert into processo (numero,data_entrada,data_baixa,parecer,id_usuario) values ('001234FRFRG','2020-10-10 13:37:25', NULL,'EM_ANDAMENTO',1);
insert into processo (numero,data_entrada,data_baixa,parecer,id_usuario) values ('003234ERFRG','2020-10-10 10:02:12', NULL,'DEFERIDO',1);
insert into processo (numero,data_entrada,data_baixa,parecer,id_usuario) values ('023234FRFRH','2020-10-10 15:37:25', NULL,'INDEFERIDO',1);

