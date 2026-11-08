CREATE TABLE productos (
                           id BIGSERIAL PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL UNIQUE,
                           descripcion VARCHAR(255),
                           stock INT NOT NULL
);

CREATE TABLE pedidos (
                         id BIGSERIAL PRIMARY KEY,
                         codigo_pedido VARCHAR(20) NOT NULL,
                         cliente VARCHAR(100) NOT NULL,
                         producto VARCHAR(100) NOT NULL,
                         cantidad INT NOT NULL,
                         estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                         codigo_verificacion VARCHAR(6) NOT NULL
);