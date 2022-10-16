--liquibase formatted sql

--changeset am:create_projects
alter table projects add owner_id integer not null references users;
create index on projects (owner_id);