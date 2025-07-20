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
('Luis', 'Torres', '12345680', 'luis@gmail.com', 0.00, 428.00, '1234'),
('María', 'Lopez', '12345681', 'maria@gmail.com', 100.00, 314.00, '1234');

DROP TABLE usuario;
DROP TABLE usuario_roles;

select*from usuario;
select*from usuario_roles;

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
(4, 1);
select*from usuario_roles;
