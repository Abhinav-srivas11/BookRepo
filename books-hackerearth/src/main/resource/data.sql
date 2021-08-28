DROP TABLE IF EXISTS BOOK;

CREATE TABLE BOOK (
    bookID int NOT NULL PRIMARY KEY,
    title varchar(100) DEFAULT NULL,
    authors varchar(70) DEFAULT NULL,
    average_rating int DEFAULT NULL,
    isbn varchar(70) DEFAULT NULL,
    language_code varchar(70) DEFAULT NULL,
    ratings_count int DEFAULT NULL,
    price int DEFAULT NULL

);