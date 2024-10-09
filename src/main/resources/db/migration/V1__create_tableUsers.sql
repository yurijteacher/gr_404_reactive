create table users (
    id bigserial primary key,
    username varchar(60),
    password varchar(60),
    role varchar(60)
)