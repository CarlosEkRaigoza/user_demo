CREATE TABLE IF NOT EXISTS usuario (
    id IDENTITY PRIMARY KEY,
    nombre VARCHAR(255),
    num_cuenta BIGINT,
    num_telefono BIGINT
);

CREATE TABLE IF NOT EXISTS direccion (
    id IDENTITY PRIMARY KEY,
    usuario_id BIGINT,
    calle VARCHAR(255),
    ciudad VARCHAR(255),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);