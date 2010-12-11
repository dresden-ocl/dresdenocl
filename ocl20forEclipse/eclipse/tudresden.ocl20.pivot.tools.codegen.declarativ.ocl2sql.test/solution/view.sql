CREATE OR REPLACE VIEW tudOclInv1 AS
(SELECT * FROM OV_Person AS self
WHERE NOT (((SELECT temp1.value
FROM OV_Grade AS temp1
INNER JOIN (SELECT FK_grade,PK_Person FROM OV_Person) AS temp2 ON temp1.PK_Grade = temp2.FK_grade
WHERE temp2.PK_Person = self.FK_supervisor) > (SELECT temp3.value
FROM OV_Grade AS temp3
WHERE temp3.PK_Grade = self.FK_grade))));

CREATE OR REPLACE VIEW tudOclInv2 AS
(SELECT * FROM OV_Faculty AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.PK_Facility) IS NULL THEN 0
  ELSE COUNT(temp1.PK_Facility)
END
FROM OV_Facility AS temp1
WHERE temp1.FK_superFacility = self.PK_Facility) >= 2)));

CREATE OR REPLACE VIEW tudOclInv3 AS
(SELECT * FROM OV_Faculty AS self
WHERE NOT (NOT EXISTS((SELECT temp1.PK_Facility
FROM OV_Facility AS temp1
WHERE (temp1.FK_superFacility = self.PK_Facility AND NOT(EXISTS(
  SELECT temp2.PK_Facility FROM OV_Institute AS temp2
  WHERE temp2.PK_Facility = temp1.PK_Facility)))))));

CREATE OR REPLACE VIEW tudOclInv4 AS
(SELECT * FROM OV_Employee AS self
WHERE NOT ((((NOT ((SELECT temp1.name
FROM OV_Grade AS temp1
WHERE temp1.PK_Grade = self.FK_grade) = 'diploma') OR (self.taxClass = 'tc1')) AND (NOT ((SELECT temp2.name
FROM OV_Grade AS temp2
WHERE temp2.PK_Grade = self.FK_grade) = 'doctor') OR (self.taxClass = 'tc2'))) AND (NOT ((SELECT temp3.name
FROM OV_Grade AS temp3
WHERE temp3.PK_Grade = self.FK_grade) = 'professor') OR (self.taxClass = 'tc3')))));

CREATE OR REPLACE VIEW tudOclInv5 AS
(SELECT * FROM OV_Facility AS self
WHERE NOT (self.FK_headOfFacility IN
  ((SELECT temp1.FK_member
FROM ASS_member_owner AS temp1
WHERE temp1.FK_owner = self.PK_Facility))));

CREATE OR REPLACE VIEW tudOclInv6 AS
(SELECT * FROM OV_Paper AS self
WHERE NOT ((NOT ((self.purpose = 'Diplom') AND (((self.inProgress = 1) AND (1=1)) OR (NOT (self.inProgress = 1) AND NOT (1=1)))) OR NOT EXISTS((SELECT temp1.FK_author
FROM ASS_author_papers AS temp1
WHERE (temp1.FK_papers = self.PK_Paper AND NOT(EXISTS(
  SELECT temp2.PK_Person FROM OV_Student AS temp2
  WHERE temp2.PK_Person = temp1.FK_author))))))));

CREATE OR REPLACE VIEW tudOclInv7 AS
(SELECT * FROM OV_Faculty AS self
WHERE NOT (((SELECT temp1.name
FROM OV_Grade AS temp1
INNER JOIN (SELECT FK_grade,PK_Person FROM OV_Person) AS temp2 ON temp1.PK_Grade = temp2.FK_grade
WHERE temp2.PK_Person = self.FK_headOfFacility) = 'professor')));

CREATE OR REPLACE VIEW tudOclInv8 AS
(SELECT * FROM OV_Grade AS self
WHERE NOT (self.name IN
  ('none'
UNION
'diploma'
UNION
'doctor'
UNION
'professor')));

CREATE OR REPLACE VIEW tudOclInv9_1 AS
(SELECT * FROM OV_Employee AS self
WHERE NOT ((NOT ((SELECT temp1.name
FROM OV_Grade AS temp1
WHERE temp1.PK_Grade = self.FK_grade) = 'doctor') OR ((SELECT CASE
  WHEN COUNT(temp2.PK_Paper) IS NULL THEN 0
  ELSE COUNT(temp2.PK_Paper)
END
FROM OV_Paper AS temp2
INNER JOIN (SELECT FK_papers,FK_author FROM ASS_author_papers) AS temp3 ON temp2.PK_Paper = temp3.FK_papers
WHERE (temp3.FK_author = self.PK_Person AND (temp2.purpose = 'Dissertation'))) = 1))));

CREATE OR REPLACE VIEW tudOclInv9_2 AS
(SELECT * FROM OV_Employee AS self
WHERE NOT ((NOT ((SELECT temp1.name
FROM OV_Grade AS temp1
WHERE temp1.PK_Grade = self.FK_grade) = 'doctor') OR ((SELECT CASE
  WHEN COUNT(temp2.PK_Paper) IS NULL THEN 0
  ELSE COUNT(temp2.PK_Paper)
END
FROM OV_Paper AS temp2
INNER JOIN (SELECT FK_papers,FK_author FROM ASS_author_papers) AS temp3 ON temp2.PK_Paper = temp3.FK_papers
WHERE (temp3.FK_author = self.PK_Person AND NOT((temp2.purpose = 'Dissertation')))) > 0))));

CREATE OR REPLACE VIEW tudOclInv10_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.FK_papers) IS NULL THEN 0
  ELSE COUNT(temp1.FK_papers)
END
FROM ASS_author_papers AS temp1
WHERE (temp1.FK_author = self.PK_Person AND (temp1.FK_papers = self.FK_currentPaper))) = 1)));

CREATE OR REPLACE VIEW tudOclInv10_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (self.FK_currentPaper IN
  ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))));

CREATE OR REPLACE VIEW tudOclInv10_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (NOT(self.FK_currentPaper NOT IN
  ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_4 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (NOT EXISTS (
  ((SELECT temp2.FK_papers
FROM ASS_author_papers AS temp2
WHERE temp2.FK_author = self.PK_Person))
  EXCEPT
  ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_5 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (NOT(NOT EXISTS (
  ((SELECT temp2.FK_papers
FROM ASS_author_papers AS temp2
WHERE temp2.FK_author = self.PK_Person))
  INTERSECT
  ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_6 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (NOT(NOT EXISTS ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_7 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))));

CREATE OR REPLACE VIEW tudOclInv10_8 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.FK_papers) IS NULL THEN 0
  ELSE COUNT(temp1.FK_papers)
END
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person) > 0)));

CREATE OR REPLACE VIEW tudOclInv10_9 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((CASE
  WHEN SUM(self.salaries) IS NULL THEN 0
  ELSE SUM(self.salaries)
END = 300)));

CREATE OR REPLACE VIEW tudOclInv11_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp2.FK_papers
FROM ASS_author_papers AS temp2
WHERE temp2.FK_author = self.FK_supervisor) INTERSECT
  (SELECT temp1.FK_papers
  FROM ASS_author_papers AS temp1
  WHERE temp1.FK_author = self.PK_Person)))));

CREATE OR REPLACE VIEW tudOclInv11_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person) UNION
  (self.FK_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person) EXCEPT
  (self.FK_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_4 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person) UNION
  (SELECT temp2.FK_papers
  FROM ASS_author_papers AS temp2
  WHERE temp2.FK_author = self.FK_supervisor)))));

CREATE OR REPLACE VIEW tudOclInv12_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))
  UNION
  (SELECT temp1.FK_papers, (SELECT MAX(SEQNO) FROM ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))) AS SEQNO
   FROM (SELECT temp2.FK_papers
FROM ASS_author_papers AS temp2
WHERE temp2.FK_author = self.FK_supervisor)))));

CREATE OR REPLACE VIEW tudOclInv12_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))
  UNION
  (SELECT self.FK_currentPaper, ((SELECT MAX(SEQNO) FROM ((SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person))) + 1) AS SEQNO))));

CREATE OR REPLACE VIEW tudOclInv12_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (EXISTS (SELECT self.FK_currentPaper,
  (SELECT COUNT(*)+1 FROM (
    SELECT self.FK_currentPaper, SEQNO
    FROM self.FK_currentPaper
    WHERE NOT (PK_Paper = self.FK_currentPaper)
  ) WHERE SEQNO < s.SEQNO) AS SEQNO
  FROM (
    SELECT self.FK_currentPaper, SEQNO
    FROM (SELECT temp1.FK_papers
FROM ASS_author_papers AS temp1
WHERE temp1.FK_author = self.PK_Person)
    WHERE NOT (PK_Paper = self.FK_currentPaper)
  ))));

CREATE OR REPLACE VIEW tudOclInv13_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((LENGTH(self.firstName) > 0)));

CREATE OR REPLACE VIEW tudOclInv13_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((LENGTH(self.firstName || self.lastName) = (LENGTH(self.firstName) + LENGTH(self.lastName)))));

CREATE OR REPLACE VIEW tudOclInv13_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((LENGTH(UPPER(self.firstName)) = LENGTH(self.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_4 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((LENGTH(LOWER(self.firstName)) = LENGTH(self.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_5 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((LENGTH(SUBSTRING(self.firstName, 1, 3 - 1 + 1)) = 3)));

CREATE OR REPLACE VIEW tudOclInv14_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((ABS(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((FLOOR(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((ROUND(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_4 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((CASE
  WHEN self.age > 1000 THEN self.age
  ELSE 1000
END = 1000)));

CREATE OR REPLACE VIEW tudOclInv14_5 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((CASE
  WHEN self.age < -1 THEN self.age
  ELSE -1
END = -1)));

CREATE OR REPLACE VIEW tudOclInv14_6 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (((self.age / 1000) < 1)));

CREATE OR REPLACE VIEW tudOclInv14_7 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.age - ((self.age / 1000) * 1000) = self.age)));

CREATE OR REPLACE VIEW tudOclInv15_1 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (('x' < 'y')));

CREATE OR REPLACE VIEW tudOclInv15_2 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (('y' <= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_3 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (('z' >= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_4 AS
(SELECT * FROM OV_Student AS self
WHERE NOT (('z' > 'y')));

CREATE OR REPLACE VIEW tudOclInv15_5 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName < 'y')));

CREATE OR REPLACE VIEW tudOclInv15_6 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName <= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_7 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName >= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_8 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName > 'y')));

CREATE OR REPLACE VIEW tudOclInv15_9 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName < self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_10 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName <= self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_11 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName >= self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_12 AS
(SELECT * FROM OV_Student AS self
WHERE NOT ((self.firstName > self.lastName)));