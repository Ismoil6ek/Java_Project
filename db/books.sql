DROP TABLE books;

CREATE TABLE books (
 id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
 author varchar (30) NOT NULL,
 title varchar (100) NOT NULL,
 subject varchar (100) NOT NULL,
 isbn varchar (20) NOT NULL,
 publishedDate varchar (20) NOT NULL,
 student varchar (30) NOT NULL,
 borrowed varchar (1) DEFAULT '0',
 given varchar(10) DEFAULT '2021-01-01',
 limit varchar(10) DEFAULT '2021-01-01',
 PRIMARY KEY (id)
);

