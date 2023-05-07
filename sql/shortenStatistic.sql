create schema shortenStatistic;

create or replace table shortenStatistic.history
(
    historyId  varchar(255) not null
        primary key,
    urlId      varchar(100) null,
    socialType tinyint      null,
    createdBy  varchar(50)  null,
    createdAt  datetime     null
);