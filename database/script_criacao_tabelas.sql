CREATE DATABASE administrador_contatos;

USE administrador_contatos;

CREATE TABLE IF NOT EXISTS usuarios (
    id     INT          AUTO_INCREMENT PRIMARY KEY,
    nome   VARCHAR(255) NOT NULL,
    email  VARCHAR(255) NOT NULL       UNIQUE,
    senha  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS contatos (
    id         INT          AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    telefone   VARCHAR(255) NOT NULL,
    usuarioId  INT          NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE
);
