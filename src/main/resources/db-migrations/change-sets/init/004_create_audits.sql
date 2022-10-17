--liquibase formatted sql

--changeset am:create_audits
create table if not exists audits
(
    id            serial            primary key,
    event         varchar(255),
    status        varchar(255),
    message       text,
    context       jsonb,
    user_id       integer,
    created_at    timestamp         default current_timestamp
);
create index on audits (event);
create index on audits (status);
create index on audits (user_id);