DROP TABLE IF EXISTS curso;

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    creditos INTEGER NOT NULL CHECK (creditos >= 0),
    area VARCHAR(100) NOT NULL
);
