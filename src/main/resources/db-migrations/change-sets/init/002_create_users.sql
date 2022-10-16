--liquibase formatted sql

--changeset am:create_users
create table if not exists users
(
    id                      serial        primary key,
    email                   varchar(255)  not null,
    password                varchar(72)   not null,
    first_name              varchar(255)  not null,
    last_name               varchar(255)  not null,
    created_at              timestamp     not null default current_timestamp
);