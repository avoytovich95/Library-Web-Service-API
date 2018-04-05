USE librarydb;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS guests;
DROP TABLE IF EXISTS bookID;
CREATE TABLE books (id INT AUTO_INCREMENT,
                    title VARCHAR(50),
                    author VARCHAR(50),
                    year INT,
                    checkedOut BOOLEAN,
                    outTo VARCHAR(50),
                    outAt VARCHAR(50),
                    PRIMARY KEY(id));
CREATE TABLE guests (id VARCHAR(50),
                     first VARCHAR(50),
                     last VARCHAR(50),
                     fee REAL,
                     PRIMARY KEY(id));
CREATE TABLE bookID (id INT);

INSERT INTO books VALUES (1000, 'Harry Potter and the Sorcerers Stone', 'JK Rowling', 1997, false, '', '');
INSERT INTO books VALUES (1001, 'Harry Potter and the Chamber of Secrets', 'JK Rowling', 1998, false, '', '');
INSERT INTO books VALUES (1002, 'Harry Potter and the Prisoner of Azkaban', 'JK Rowling', 1999, false, '', '');
INSERT INTO books VALUES (1003, 'Harry Potter and the Goblet of Fire', 'JK Rowling', 2000, false, '', '');
INSERT INTO books VALUES (1004, 'Harry Potter and the order of the Phoenix', 'JK Rowling', 2003, false, '', '');
INSERT INTO books VALUES (1005, 'Harry Potter and the Half-Blood Prince', 'JK Rowling', 2005, false, '', '');
INSERT INTO books VALUES (1006, 'Harry Potter and the Deathly Hallows', 'JK Rowling', 2007, false, '', '');

INSERT INTO books VALUES (1007, 'A Game of Thrones', 'George RR Martin', 1996, false, '', '');
INSERT INTO books VALUES (1008, 'A Clash of Kings', 'George RR Martin', 1999, false, '', '');
INSERT INTO books VALUES (1009, 'A Storm of Swords', 'George RR Martin', 2000, false, '', '');
INSERT INTO books VALUES (1010, 'A Feast for Crows', 'George RR Martin', 2005, false, '', '');
INSERT INTO books VALUES (1011, 'A Dance with Dragons', 'George RR Martin', 2011, false, '', '');

INSERT INTO books VALUES (1012, 'The Hunger Games', 'Suzanne Collins', 2008, false, '', '');
INSERT INTO books VALUES (1013, 'Catching Fire', 'Suzanne Collins', 2009, false, '', '');
INSERT INTO books VALUES (1014, 'Mockingjay', 'Suzanne Collins', 2010, false, '', '');

INSERT INTO books VALUES (1015, 'To Kill a Mockingbird', 'Harper Lee', 1960, false, '', '');
INSERT INTO books VALUES (1016, 'Pride and Prejudice', 'Jane Austen', 1813,false, '', '');
INSERT INTO books VALUES (1017, 'The Book Thief', 'Markus Zusak', 2005, false, '', '');
INSERT INTO books VALUES (1018, 'Animal Farm', 'George Orwell', 1945, false, '', '');
INSERT INTO books VALUES (1019, 'Gone with the Wind', 'Margaret Mitchell', 1936, false, '', '');
INSERT INTO books VALUES (1020, 'The Hitchhikers Guide to the Galaxy', 'Douglas  Adams', 1979, false, '', '');
INSERT INTO books VALUES (1021, 'The Giving Tree', 'Shel Silverstein', 1964, false, '', '');
INSERT INTO books VALUES (1022, 'The Da Vinci Code', 'Dan Brown', 2003, false, '', '');
INSERT INTO books VALUES (1023, 'Les Miserables', 'Victor Hugo', 1862, false, '', '');
INSERT INTO books VALUES (1024, 'Lord of the Flies', 'William Golding', 1954, false, '', '');
INSERT INTO books VALUES (1025, 'The Alchemist', 'Paulo Coelho', 1988, false, '', '');
INSERT INTO books VALUES (1026, 'Crime and Punishment', 'Fyodor Dostoyevsky', 1866, false, '', '');
INSERT INTO books VALUES (1027, 'Altered Carob', 'Richard Morgan', 2002, false, '', '');