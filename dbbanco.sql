SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DROP DATABASE dbbanco;

CREATE DATABASE dbbanco;
use dbbanco;

CREATE TABLE `roles` (
    `id_roles` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (nombre) VALUES 
('ROLE_USER'),
('ROLE_OPERADOR'),
('ROLE_ANALISTA');

select*from roles;

CREATE TABLE IF NOT EXISTS `usuario` (
    `id_usuario` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(30) NOT NULL,
    `apellidos` VARCHAR(30) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    `saldo_disponible` DECIMAL(10,2),
    `saldo_contable` DECIMAL(10,2),
    password VARCHAR(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO usuario (nombre, apellidos, dni, email, saldo_disponible, saldo_contable, password) VALUES
-- Carlos tiene el rol Operador
('Carlos', 'Ramírez', '12345678', 'carlos@admin.com', 0.00, 0.00, '1234'),
-- Ana tiene el rol Analista
('Ana', 'Pérez', '12345679', 'ana@admin.com', 0.00, 0.00, '1234'),
('Luis', 'Torres', '12345680', 'luis@gmail.com', 1500.00, 1600.00, '1234'),
('María', 'Lopez', '12345681', 'maria@gmail.com', 500.00, 550.00, '1234'),
('Jorge', 'Fernández', '12345682', 'jorge@gmail.com', 700.00, 800.00, '1234'),
('Lucía', 'Martínez', '12345683', 'lucia@gmail.com', 950.00, 1000.00, '1234'),
('Pedro', 'Gonzales', '12345684', 'pedro@gmail.com', 1230.00, 1300.00, '1234'),
('Rosa', 'Vega', '12345685', 'rosa@gmail.com', 880.00, 900.00, '1234'),
('Daniel', 'Quispe', '12345686', 'daniel@gmail.com', 1100.00, 1150.00, '1234'),
('Elena', 'Castro', '12345687', 'elena@gmail.com', 990.00, 1000.00, '1234');


select*from usuario;


CREATE TABLE `usuario_roles` (
    `id_usuario` INT NOT NULL,
    `id_roles` INT NOT NULL,
    PRIMARY KEY (id_usuario, id_roles),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_roles) REFERENCES roles(id_roles) ON DELETE CASCADE
);

INSERT INTO usuario_roles (id_usuario, id_roles) VALUES (1, 2);

INSERT INTO usuario_roles (id_usuario, id_roles) VALUES (2, 3);

INSERT INTO usuario_roles (id_usuario, id_roles) VALUES 
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1);
select*from usuario_roles;
