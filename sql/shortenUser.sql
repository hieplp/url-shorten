create schema shortenUser;

create table shortenUser.password
(
    userId   varchar(100) not null
        primary key,
    password binary(64) null,
    salt     binary(64) null
);

create table shortenUser.user
(
    userId     varchar(100) not null
        primary key,
    username   varchar(100) null,
    status     tinyint null,
    createdBy  varchar(100) null,
    createdAt  datetime null,
    modifiedBy varchar(100) null,
    modifiedAt datetime null,
    constraint username
        unique (username)
);
