CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50),
                       email VARCHAR(50) UNIQUE,
                       password TEXT
);