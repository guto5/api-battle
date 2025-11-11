CREATE TABLE usuarios (
                          id          BIGSERIAL PRIMARY KEY,  -- BIGSERIAL é um auto-incremento de 64 bits (para Long)
                          email       VARCHAR(255) NOT NULL UNIQUE,
                          senha       VARCHAR(255) NOT NULL,
                          role        VARCHAR(50) NOT NULL
);

-- Cria a tabela de posts
CREATE TABLE posts (
                       id          BIGSERIAL PRIMARY KEY,
                       quem        VARCHAR(255) NOT NULL,
                       data_hora   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Mapeia para 'dataHora'
                       comentario  TEXT,
                       curtidas    INTEGER DEFAULT 0
);