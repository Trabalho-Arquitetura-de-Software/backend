CREATE TYPE user_role AS ENUM ('ADMIN', 'PROFESSOR', 'STUDENT'); -- Definindo o tipo ENUM para as roles

CREATE TABLE USERS
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name     VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password TEXT                NOT NULL,
    role     user_role           NOT NULL -- Utilizando o ENUM para garantir que seja uma role válida
);