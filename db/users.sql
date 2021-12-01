DROP TABLE users;

CREATE TABLE users (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   login varchar (30) NOT NULL UNIQUE,
   name varchar (30) NOT NULL,
   role varchar (20) NOT NULL,
   password varchar(16) NOT NULL,
   PRIMARY KEY (id)
);


INSERT INTO users (login, name, role, password) VALUES
            ('asadbek', 'Asadbek', 'admin', '201iut'),
            ('maruf','Maruf','librarian','1'),
            ('jamshid','Jamshid','student','121');