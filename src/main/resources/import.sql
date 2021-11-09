INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (1, 'Eduardo', 'Flores', 'test1@test.com', '2021-11-01', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (2, 'Luis', 'Ramirez', 'test2@test.com', '2021-11-01', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (3, 'Dewey', 'Flores', 'test3@test.com', '2021-11-02', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (4, 'Luis', 'Alcaraz', 'test4@test.com', '2021-11-02', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (5, 'Susana', 'Garcia', 'test5@test.com', '2021-11-02', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (6, 'Liliana', 'Ramirez', 'test6@test.com', '2021-11-02', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (7, 'Eduardo', 'Alarcon', 'test7@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (8, 'Luis', 'Arturo', 'test8@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (9, 'Angel', 'Velez', 'test9@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (10, 'Luis', 'Ortiz', 'test10@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (11, 'Eduardo', 'Mondragon', 'test11@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (12, 'Amelia', 'Valladarez', 'test12@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (13, 'Jorge', 'Rodriguez', 'test13@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (14, 'Grisell', 'Valeriano', 'test14@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (15, 'Raul', 'Flores', 'test15@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (16, 'Luis', 'Godinez', 'test16@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (17, 'Edward', 'Oyola', 'test17@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (18, 'Angelica', 'Aguilar', 'test18@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (19, 'Altamirano', 'Garcia', 'test19@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (20, 'Angel', 'Ramirez', 'test20@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (21, 'Javier', 'Avila', 'test21@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (22, 'Valeria', 'Ramirez', 'test22@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (23, 'Cristian', 'Valenzuela', 'test23@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (24, 'Joana', 'Ramirez', 'test24@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (25, 'Guadalupe', 'Vargas', 'test25@test.com', '2021-11-03', '');
INSERT INTO clientes (id, nombre, apellido, email, create_at, foto) VALUES (26, 'Alfonso', 'Rodriguez', 'test26@test.com', '2021-11-03', '');/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES ('Xiaomi Pocophone F3', 9000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Play Station 5', 14000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Apple Iphone 13 Pro', 27000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Apple Iphone 13 Pro Max', 30000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Xiaomi Pocophone X3 Pro', 7000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Play Station 4', 7000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Pantalla LCD Sony', 9000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Laptop HP Ryzen 5 16 GB RAM', 34000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Drone DJI Tello', 19000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Memoria USB 250 GB', 1000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Camara Canon', 10000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Audifonos alambricos JBL', 450, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Audifonos Xiaomi inalambricos', 600, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES ('Mi Band 6 Xiaomi', 1200, NOW());

/* Facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura equipos electronicos', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (5, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1, 1, 8);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES ('Factura telefonos', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (3, 2, 2);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (3, 2, 2);




