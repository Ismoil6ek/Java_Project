DROP TABLE librarians;

CREATE TABLE librarians (
 id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
 nameN varchar (30) NOT NULL,
 login varchar (30) NOT NULL,
 password varchar (30) NOT NULL,
 roleN varchar (30) NOT NULL,
 PRIMARY KEY (id)
);

INSERT INTO librarians (nameN,login,password,roleN) VALUES ('Maruf','maruf','1','librarian');