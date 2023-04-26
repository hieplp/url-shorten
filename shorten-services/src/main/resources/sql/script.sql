create table shortenUrl.password
(
    userId   varchar(100) not null
        primary key,
    password binary(64) null,
    salt     binary(64) null
);

create table shortenUrl.url
(
    urlId      varchar(50) not null
        primary key,
    shortUrl   varchar(50) null,
    longUrl    text null,
    alias      varchar(50) null,
    expiredAt  datetime null,
    status     tinyint default 0 null,
    createdBy  varchar(50) null,
    createdAt  datetime null,
    modifiedBy varchar(50) null,
    modifiedAt datetime null,
    isDeleted  tinyint default 0 null,
    deletedBy  varchar(50) null,
    deletedAt  datetime null
);

create index url_shortUrl_status_index
    on shortenUrl.url (shortUrl, status);

create table shortenUrl.user
(
    userId     varchar(100) not null
        primary key,
    username   varchar(100) null,
    status     tinyint null,
    createdBy  varchar(100) null,
    createdAt  datetime null,
    modifiedBy varchar(100) null,
    modifiedAt datetime null
);

