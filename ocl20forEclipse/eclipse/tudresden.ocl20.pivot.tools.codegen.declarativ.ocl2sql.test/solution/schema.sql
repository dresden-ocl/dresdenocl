CREATE TABLE ASS_author_papers (
FK_author VARCHAR(255),FK_papers VARCHAR(255)
);

CREATE TABLE ASS_member_owner (
FK_member VARCHAR(255),FK_owner VARCHAR(255)
);

CREATE TABLE T_Facility (
PK_Facility VARCHAR(255) PRIMARY KEY,FK_headOfFacility VARCHAR(255),FK_superFacility VARCHAR(255),name VARCHAR(255),type VARCHAR(255)
);

CREATE TABLE T_Grade (
PK_Grade VARCHAR(255) PRIMARY KEY,name VARCHAR(255),value INT
);

CREATE TABLE T_Paper (
PK_Paper VARCHAR(255) PRIMARY KEY,category VARCHAR(255),edition VARCHAR(255),inProgress NUMBER(1),purpose VARCHAR(255),title VARCHAR(255)
);

CREATE TABLE T_Person (
PK_Person VARCHAR(255) PRIMARY KEY,FK_currentPaper VARCHAR(255),FK_grade VARCHAR(255),FK_supervisor VARCHAR(255),FK_theFacility VARCHAR(255),age INT,birthDate DATE,dissSubject VARCHAR(255),firstName VARCHAR(255),isMarried NUMBER(1),lastName VARCHAR(255),matDate DATE,matNr INT,salaries VARCHAR(255),soSecNr VARCHAR(255),taxClass VARCHAR(255),type VARCHAR(255),wage INT
);

CREATE VIEW OV_Chair
AS ( SELECT PK_Facility,T_Facility.name AS name,FK_headOfFacility,FK_superFacility
FROM T_Facility
WHERE type = "Chair" );

CREATE VIEW OV_Employee
AS ( SELECT PK_Person,T_Person.age AS age,T_Person.birthDate AS birthDate,T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,T_Person.salaries AS salaries,T_Person.soSecNr AS soSecNr,T_Person.taxClass AS taxClass,T_Person.wage AS wage,FK_currentPaper,FK_grade,FK_supervisor,FK_theFacility
FROM T_Person
WHERE type = "Employee" );

CREATE VIEW OV_Facility
AS ( SELECT PK_Facility,T_Facility.name AS name,FK_headOfFacility,FK_superFacility
FROM T_Facility
 );

CREATE VIEW OV_Faculty
AS ( SELECT PK_Facility,T_Facility.name AS name,FK_headOfFacility,FK_superFacility
FROM T_Facility
WHERE type = "Faculty" );

CREATE VIEW OV_Grade
AS ( SELECT PK_Grade,T_Grade.name AS name,T_Grade.value AS value
FROM T_Grade
 );

CREATE VIEW OV_Institute
AS ( SELECT PK_Facility,T_Facility.name AS name,FK_headOfFacility,FK_superFacility
FROM T_Facility
WHERE type = "Institute" );

CREATE VIEW OV_Paper
AS ( SELECT PK_Paper,T_Paper.category AS category,T_Paper.edition AS edition,T_Paper.inProgress AS inProgress,T_Paper.purpose AS purpose,T_Paper.title AS title
FROM T_Paper
 );

CREATE VIEW OV_Person
AS ( SELECT PK_Person,T_Person.age AS age,T_Person.birthDate AS birthDate,T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,T_Person.salaries AS salaries,FK_currentPaper,FK_grade,FK_supervisor,FK_theFacility
FROM T_Person
 );

CREATE VIEW OV_PhDStudent
AS ( SELECT PK_Person,T_Person.age AS age,T_Person.birthDate AS birthDate,T_Person.dissSubject AS dissSubject,T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,T_Person.salaries AS salaries,T_Person.soSecNr AS soSecNr,T_Person.taxClass AS taxClass,T_Person.wage AS wage,FK_currentPaper,FK_grade,FK_supervisor,FK_theFacility
FROM T_Person
WHERE type = "PhDStudent" );

CREATE VIEW OV_Student
AS ( SELECT PK_Person,T_Person.age AS age,T_Person.birthDate AS birthDate,T_Person.firstName AS firstName,T_Person.isMarried AS isMarried,T_Person.lastName AS lastName,T_Person.matDate AS matDate,T_Person.matNr AS matNr,T_Person.salaries AS salaries,FK_currentPaper,FK_grade,FK_supervisor,FK_theFacility
FROM T_Person
WHERE type = "Student" );

ALTER TABLE ASS_author_papers ADD CONSTRAINT CONASS_author_papersFK_author
FOREIGN KEY (FK_author) REFERENCES T_Person(PK_Person);

ALTER TABLE T_Person ADD CONSTRAINT CONT_PersonFK_currentPaper
FOREIGN KEY (FK_currentPaper) REFERENCES T_Paper(PK_Paper);

ALTER TABLE T_Person ADD CONSTRAINT CONT_PersonFK_grade
FOREIGN KEY (FK_grade) REFERENCES T_Grade(PK_Grade);

ALTER TABLE T_Facility ADD CONSTRAINT CONT_FacilityFK_headOfFacility
FOREIGN KEY (FK_headOfFacility) REFERENCES T_Person(PK_Person);

ALTER TABLE ASS_member_owner ADD CONSTRAINT CONASS_member_ownerFK_member
FOREIGN KEY (FK_member) REFERENCES T_Person(PK_Person);

ALTER TABLE ASS_member_owner ADD CONSTRAINT CONASS_member_ownerFK_owner
FOREIGN KEY (FK_owner) REFERENCES T_Facility(PK_Facility);

ALTER TABLE ASS_author_papers ADD CONSTRAINT CONASS_author_papersFK_papers
FOREIGN KEY (FK_papers) REFERENCES T_Paper(PK_Paper);

ALTER TABLE T_Facility ADD CONSTRAINT CONT_FacilityFK_superFacility
FOREIGN KEY (FK_superFacility) REFERENCES T_Facility(PK_Facility);

ALTER TABLE T_Person ADD CONSTRAINT CONT_PersonFK_supervisor
FOREIGN KEY (FK_supervisor) REFERENCES T_Person(PK_Person);

ALTER TABLE T_Person ADD CONSTRAINT CONT_PersonFK_theFacility
FOREIGN KEY (FK_theFacility) REFERENCES T_Facility(PK_Facility);