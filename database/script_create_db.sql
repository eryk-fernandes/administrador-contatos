CREATE DATABASE contact_manager;

USE contact_manager;

CREATE TABLE IF NOT EXISTS users (
    id        INT           AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255)  NOT NULL,
    email     VARCHAR(255)  NOT NULL       UNIQUE,
    password  VARCHAR(255)  NOT NULL
);

CREATE TABLE IF NOT EXISTS contacts (
    id      INT           AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255)  NOT NULL,
    email   VARCHAR(255)  NOT NULL,
    number  VARCHAR(255)  NOT NULL,
    userId  INT           NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
);
