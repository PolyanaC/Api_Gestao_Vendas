CREATE table categoria(
	codigo BIGSERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL	
);

SET client_encoding to 'UTF8';

insert into categoria (nome) values('Tecnologia');
insert into categoria (nome) values('Acessórios para veículos');
insert into categoria (nome) values('Esporte e Lazer');
insert into categoria (nome) values('Casa e Eletrodomésticos');
insert into categoria (nome) values('Joias e Relógios');