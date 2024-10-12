CREATE DATABASE Segunda_Vida;
USE Segunda_Vida;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    correo_electronico VARCHAR(100) UNIQUE NOT NULL,
    verificado BOOLEAN DEFAULT FALSE -- Columna para verificar la identidad
);

-- Tabla de Categorias
CREATE TABLE Categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

-- Tabla de Productos
CREATE TABLE Productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    nombre_articulo VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    id_categoria INT, -- Relación directa con la tabla de categorías
    descripcion TEXT,
    condicion ENUM('Nuevo', 'Usado') NOT NULL, -- Usando ENUM para condicionar el estado del producto
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria) ON DELETE CASCADE
);

-- ImagenesProductos
CREATE TABLE ImagenesProducto (
    id_imagen INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT, -- Relacionado con la tabla Productos
    url_imagen VARCHAR(500) NOT NULL, -- URL de Firebase donde se aloja la imagen
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) ON DELETE CASCADE
);

-- Tabla de Compras
CREATE TABLE Compras (
    id_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_comprador INT,
    id_producto INT,
    fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_comprador) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) ON DELETE CASCADE
);

-- Tabla de Ventas
CREATE TABLE Ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_vendedor INT,
    id_producto INT,
    fecha_venta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_vendedor) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto) ON DELETE CASCADE
);

-- Tabla de Reseñas (Vinculada a Compras)
CREATE TABLE Resenas (
    id_resena INT AUTO_INCREMENT PRIMARY KEY,
    id_compra INT, -- Relacionada con la compra realizada
    comentario TEXT,
    puntuacion INT CHECK (puntuacion >= 1 AND puntuacion <= 5), -- Validar puntuación entre 1 y 5
    fecha_resena TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_compra) REFERENCES Compras(id_compra) ON DELETE CASCADE
);

-- Insertar datos de prueba en la tabla Usuarios
INSERT INTO Usuarios (nombre_completo, nombre_usuario, contrasena, telefono, correo_electronico, verificado)
VALUES
('Juan Pérez', 'juan123', 'contrasenaSegura123', '555-1234', 'juan.perez@example.com', TRUE),
('Ana Gómez', 'ana_gomez', 'gomezSegura456', '555-5678', 'ana.gomez@example.com', FALSE),
('Carlos Ramírez', 'carlos_r', 'ramirezPassword789', '555-7890', 'carlos.ramirez@example.com', TRUE),
('Lucía Fernández', 'lucy_f', 'fernandezSeguro101', '555-4567', 'lucia.fernandez@example.com', FALSE),
('David Torres', 'davidtorres', 'torresClave102', '555-9876', 'david.torres@example.com', TRUE);
select * from usuarios;


