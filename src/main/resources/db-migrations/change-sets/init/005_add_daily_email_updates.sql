--liquibase formatted sql

--changeset am:create_projects
alter table users add daily_email_updates booleannot;