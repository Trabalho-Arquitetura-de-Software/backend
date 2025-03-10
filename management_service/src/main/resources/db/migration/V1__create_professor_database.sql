CREATE TABLE professor_users
(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    registrationNumber BIGINT UNIQUE NOT NULL,
    deleted BOOLEAN DEFAULT FALSE
    );
