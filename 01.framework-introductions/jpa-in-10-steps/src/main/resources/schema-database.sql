create schema if not exists facebook;
create table if not exists facebook.member (
    account Long primary key auto_increment,
    name VARCHAR(255)
);

create schema if not exists twitter;
create table if not exists twitter.member (
   account Long primary key auto_increment,
    name VARCHAR(255)
)
