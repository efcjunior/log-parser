create database if not exists db_parser;

use db_parser;

create table if not exists request_http_log(
    dt datetime(3),
    ip varchar(20),
    status_code smallint,
    user_agent varchar(200)
);
