CREATE TABLE customer (
	id serial PRIMARY KEY,
	name VARCHAR ( 60 ) UNIQUE NOT NULL,
	cpf VARCHAR ( 14 ) UNIQUE NOT NULL,
	age VARCHAR ( 3 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	phone VARCHAR ( 20 ) NOT NULL
	);