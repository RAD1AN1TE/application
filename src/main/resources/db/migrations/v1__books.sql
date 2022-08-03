--liquibase formatted sql

--changeset Books:create-books-table
CREATE TABLE IF NOT EXISTS books(

    "bookId"    int           NOT NULL,
    "title"     varchar(250),
    "author"    varchar(50),
    "owned"     bool,

    PRIMARY KEY ("bookId")

);
--changeset Books:update-books-table-01
ALTER TABLE books ADD COLUMN IF NOT EXISTS about1 varchar(250);
