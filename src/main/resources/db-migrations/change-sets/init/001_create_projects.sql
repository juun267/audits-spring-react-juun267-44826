--liquibase formatted sql

--changeset am:create_projects
create table if not exists projects
(
    id          serial   primary key,
    title       text     not null,
    description text     not null,
);