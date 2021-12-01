DROP TABLE students;

CREATE TABLE students (
 id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
 firstname varchar (30) NOT NULL,
 lastname varchar (30) NOT NULL,
 studentID varchar (30) NOT NULL,
 login varchar (30) NOT NULL,
 password varchar (30) NOT NULL,
 roleS varchar (30) NOT NULL,
 status varchar(1) NOT NULL,
 fine varchar (30) NOT NULL,
 PRIMARY KEY (id)
);

INSERT INTO students (firstName,lastName,studentID,login,password,roleS,status,fine) VALUES('Jamshid','Umarov','U1910215','jamshid','121','student','0','0');

