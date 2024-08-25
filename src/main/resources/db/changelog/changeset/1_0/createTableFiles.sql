--liquibase formatted sql

--changeset Dmitry Smirnov:1_0
--comment: Создание таблицы для хранения файлов

--Файлы
CREATE TABLE IF NOT EXISTS files
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(250) NOT NULL UNIQUE,
    creation_date TIMESTAMP      NOT NULL,
    description VARCHAR(250) NOT NULL,
    file TEXT NOT NULL
);