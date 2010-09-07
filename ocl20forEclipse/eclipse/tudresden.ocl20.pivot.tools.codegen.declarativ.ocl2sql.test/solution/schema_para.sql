CREATE TABLE AS_author_papers (
F_author VARCHAR(255),F_papers VARCHAR(255)
);

CREATE TABLE AS_member_owner (
F_member VARCHAR(255),F_owner VARCHAR(255)
);

CREATE TABLE TB_Facility (
P_Facility VARCHAR(255) PRIMARY KEY,F_headOfFacility VARCHAR(255),F_superFacility VARCHAR(255),name VARCHAR(255),type VARCHAR(255)
);

CREATE TABLE TB_Grade (
P_Grade VARCHAR(255) PRIMARY KEY,F_person VARCHAR(255),name VARCHAR(255),value INT
);

CREATE TABLE TB_Paper (
P_Paper VARCHAR(255) PRIMARY KEY,F_person VARCHAR(255),category VARCHAR(255),edition VARCHAR(255),inProgress NUMBER(1),purpose VARCHAR(255),title VARCHAR(255)
);

CREATE TABLE TB_Person (
P_Person VARCHAR(255) PRIMARY KEY,F_currentPaper VARCHAR(255),F_grade VARCHAR(255),F_supervisor VARCHAR(255),F_theFacility VARCHAR(255),age INT,birthDate DATE,dissSubject VARCHAR(255),firstName VARCHAR(255),isMarried NUMBER(1),lastName VARCHAR(255),matDate DATE,matNr INT,salaries VARCHAR(255),soSecNr VARCHAR(255),taxClass VARCHAR(255),type VARCHAR(255),wage INT
);

CREATE VIEW O_Chair
AS ( SELECT P_Facility,TB_Facility.name AS name,F_headOfFacility,F_superFacility
FROM TB_Facility
WHERE type = "Chair" );

CREATE VIEW O_Employee
AS ( SELECT P_Person,TB_Person.age AS age,TB_Person.birthDate AS birthDate,TB_Person.firstName AS firstName,TB_Person.isMarried AS isMarried,TB_Person.lastName AS lastName,TB_Person.salaries AS salaries,TB_Person.soSecNr AS soSecNr,TB_Person.taxClass AS taxClass,TB_Person.wage AS wage,F_currentPaper,F_grade,F_supervisor,F_theFacility
FROM TB_Person
WHERE type = "Employee" );

CREATE VIEW O_Facility
AS ( SELECT P_Facility,TB_Facility.name AS name,F_headOfFacility,F_superFacility
FROM TB_Facility
 );

CREATE VIEW O_Faculty
AS ( SELECT P_Facility,TB_Facility.name AS name,F_headOfFacility,F_superFacility
FROM TB_Facility
WHERE type = "Faculty" );

CREATE VIEW O_Grade
AS ( SELECT P_Grade,TB_Grade.name AS name,TB_Grade.value AS value,F_person
FROM TB_Grade
 );

CREATE VIEW O_Institute
AS ( SELECT P_Facility,TB_Facility.name AS name,F_headOfFacility,F_superFacility
FROM TB_Facility
WHERE type = "Institute" );

CREATE VIEW O_Paper
AS ( SELECT P_Paper,TB_Paper.category AS category,TB_Paper.edition AS edition,TB_Paper.inProgress AS inProgress,TB_Paper.purpose AS purpose,TB_Paper.title AS title,F_person
FROM TB_Paper
 );

CREATE VIEW O_Person
AS ( SELECT P_Person,TB_Person.age AS age,TB_Person.birthDate AS birthDate,TB_Person.firstName AS firstName,TB_Person.isMarried AS isMarried,TB_Person.lastName AS lastName,TB_Person.salaries AS salaries,F_currentPaper,F_grade,F_supervisor,F_theFacility
FROM TB_Person
 );

CREATE VIEW O_PhDStudent
AS ( SELECT P_Person,TB_Person.age AS age,TB_Person.birthDate AS birthDate,TB_Person.dissSubject AS dissSubject,TB_Person.firstName AS firstName,TB_Person.isMarried AS isMarried,TB_Person.lastName AS lastName,TB_Person.salaries AS salaries,TB_Person.soSecNr AS soSecNr,TB_Person.taxClass AS taxClass,TB_Person.wage AS wage,F_currentPaper,F_grade,F_supervisor,F_theFacility
FROM TB_Person
WHERE type = "PhDStudent" );

CREATE VIEW O_Student
AS ( SELECT P_Person,TB_Person.age AS age,TB_Person.birthDate AS birthDate,TB_Person.firstName AS firstName,TB_Person.isMarried AS isMarried,TB_Person.lastName AS lastName,TB_Person.matDate AS matDate,TB_Person.matNr AS matNr,TB_Person.salaries AS salaries,F_currentPaper,F_grade,F_supervisor,F_theFacility
FROM TB_Person
WHERE type = "Student" );

ALTER TABLE AS_author_papers ADD CONSTRAINT CONAS_author_papersF_author
FOREIGN KEY (F_author) REFERENCES TB_Person(P_Person);

ALTER TABLE TB_Person ADD CONSTRAINT CONTB_PersonF_currentPaper
FOREIGN KEY (F_currentPaper) REFERENCES TB_Paper(P_Paper);

ALTER TABLE TB_Person ADD CONSTRAINT CONTB_PersonF_grade
FOREIGN KEY (F_grade) REFERENCES TB_Grade(P_Grade);

ALTER TABLE TB_Facility ADD CONSTRAINT CONTB_FacilityF_headOfFacility
FOREIGN KEY (F_headOfFacility) REFERENCES TB_Person(P_Person);

ALTER TABLE AS_member_owner ADD CONSTRAINT CONAS_member_ownerF_member
FOREIGN KEY (F_member) REFERENCES TB_Person(P_Person);

ALTER TABLE AS_member_owner ADD CONSTRAINT CONAS_member_ownerF_owner
FOREIGN KEY (F_owner) REFERENCES TB_Facility(P_Facility);

ALTER TABLE AS_author_papers ADD CONSTRAINT CONAS_author_papersF_papers
FOREIGN KEY (F_papers) REFERENCES TB_Paper(P_Paper);

ALTER TABLE TB_Grade ADD CONSTRAINT CONTB_GradeF_person
FOREIGN KEY (F_person) REFERENCES TB_Person(P_Person);

ALTER TABLE TB_Paper ADD CONSTRAINT CONTB_PaperF_person
FOREIGN KEY (F_person) REFERENCES TB_Person(P_Person);

ALTER TABLE TB_Facility ADD CONSTRAINT CONTB_FacilityF_superFacility
FOREIGN KEY (F_superFacility) REFERENCES TB_Facility(P_Facility);

ALTER TABLE TB_Person ADD CONSTRAINT CONTB_PersonF_supervisor
FOREIGN KEY (F_supervisor) REFERENCES TB_Person(P_Person);

ALTER TABLE TB_Person ADD CONSTRAINT CONTB_PersonF_theFacility
FOREIGN KEY (F_theFacility) REFERENCES TB_Facility(P_Facility);