create table cliente(
id Integer primary key auto_increment,
nome varchar(100),
cpf varchar(11)
);
create table produto(
id Integer primary key auto_increment,
descricao varchar(100),
preco_unitario numeric(7,2)
);
create table pedido(
id Integer primary key auto_increment,
cliente_id Integer references cliente(id),
data_pedido timestamp,
status varchar(20),
total numeric(7,2)
);

create table item_pedido(
id Integer primary key auto_increment,
pedido_id Integer references pedido(id),
produto_id  Integer references produto(id),
quantidade Integer
);

