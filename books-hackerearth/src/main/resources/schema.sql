

--DROP TABLE IF EXISTS book;

create table book (
    bookID int not null primary key,
    title varchar(100) default null,
    authors varchar(70) default null,
    average_rating varchar(70) default null,
    isbn varchar(70) default null,
    language_code varchar(70) default null,
    ratings_count varchar(70) default null,
    price varchar(70) default null

);
