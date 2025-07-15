DROP TABLE Funcionario;
DROP TABLE Pessoa;
DROP TABLE Editora;
DROP TABLE Livro;
DROP TABLE Pedido;
DROP TABLE Item;

CREATE TABLE IF NOT EXISTS Pessoa (
	cpf VARCHAR(11) PRIMARY KEY,
	
	nome VARCHAR(100) NOT NULL,
	endereco VARCHAR(100),
	cidade VARCHAR(50),	
	estado VARCHAR(2),
	telefone VARCHAR(14),
	email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Funcionario (
	id_funcionario INTEGER PRIMARY KEY AUTOINCREMENT,
	
	senha VARCHAR(50) NOT NULL,
	data_contrato DATE NOT NULL,
	administrador BOOLEAN NOT NULL,
	
	cpf VARCHAR(11) NOT NULL UNIQUE,
	CONSTRAINT fk_funcionario_pessoa FOREIGN KEY (cpf) REFERENCES Pessoa (cpf)
);

CREATE TABLE IF NOT EXISTS Editora (
	id_editora INTEGER PRIMARY KEY AUTOINCREMENT,
	
	nome VARCHAR(50) NOT NULL,
	cnpj VARCHAR(14) NOT NULL UNIQUE,
	endereco VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Livro (
	id_livro INTEGER PRIMARY KEY AUTOINCREMENT,
	
	titulo VARCHAR(50) NOT NULL,
	genero VARCHAR(50) NOT NULL,
	data_publicada DATE,
	autor VARCHAR(100),
	qtd_estoque INTEGER NOT NULL,
	preco_unit REAL NOT NULL,
	
	id_editora INTEGER NOT NULL,
	CONSTRAINT fk_livro_editora FOREIGN KEY (id_editora) REFERENCES Editora (id_editora)
);

CREATE TABLE IF NOT EXISTS Item (
	id_item INTEGER PRIMARY KEY AUTOINCREMENT,
	
	qtd_vendida INTEGER NOT NULL,
	preco_total REAL NOT NULL,
	
	id_livro INTEGER NOT NULL,
	id_pedido INTEGER NOT NULL,
	CONSTRAINT fk_item_pedido FOREIGN KEY (id_pedido) REFERENCES Pedido (id_pedido),
	CONSTRAINT fk_item_livro FOREIGN KEY (id_livro) REFERENCES Livro (id_livro)
);

CREATE TABLE IF NOT EXISTS Pedido (
	id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,
	
	data_pedido DATE NOT NULL,
	preco_total REAL DEFAULT 0,
	forma_pagamento VARCHAR(20),
	operacao VARCHAR(20) NOT NULL,
	
	id_funcionario INTEGER NOT NULL,
	cpf_cliente VARCHAR(11),
	CONSTRAINT fk_pedido_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario (id_funcionario),
	CONSTRAINT fk_pedido_pessoa FOREIGN KEY (cpf_cliente) REFERENCES Pessoa (cpf)
);