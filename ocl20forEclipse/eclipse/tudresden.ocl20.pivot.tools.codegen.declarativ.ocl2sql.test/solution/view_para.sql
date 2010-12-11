CREATE OR REPLACE VIEW tudOclInv1 AS
(SELECT * FROM O_Person AS self
WHERE NOT (((SELECT temp1.value
FROM O_Grade AS temp1
INNER JOIN (SELECT F_grade,P_Person FROM O_Person) AS temp2 ON temp1.P_Grade = temp2.F_grade
WHERE temp2.P_Person = self.F_supervisor) > (SELECT temp3.value
FROM O_Grade AS temp3
WHERE temp3.P_Grade = self.F_grade))));

CREATE OR REPLACE VIEW tudOclInv2 AS
(SELECT * FROM O_Faculty AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.P_Facility) IS NULL THEN 0
  ELSE COUNT(temp1.P_Facility)
END
FROM O_Facility AS temp1
WHERE temp1.F_superFacility = self.P_Facility) >= 2)));

CREATE OR REPLACE VIEW tudOclInv3 AS
(SELECT * FROM O_Faculty AS self
WHERE NOT (NOT EXISTS((SELECT temp1.P_Facility
FROM O_Facility AS temp1
WHERE (temp1.F_superFacility = self.P_Facility AND NOT(EXISTS(
  SELECT temp2.P_Facility FROM O_Institute AS temp2
  WHERE temp2.P_Facility = temp1.P_Facility)))))));

CREATE OR REPLACE VIEW tudOclInv4 AS
(SELECT * FROM O_Employee AS self
WHERE NOT ((((NOT ((SELECT temp1.name
FROM O_Grade AS temp1
WHERE temp1.P_Grade = self.F_grade) = 'diploma') OR (self.taxClass = 'tc1')) AND (NOT ((SELECT temp2.name
FROM O_Grade AS temp2
WHERE temp2.P_Grade = self.F_grade) = 'doctor') OR (self.taxClass = 'tc2'))) AND (NOT ((SELECT temp3.name
FROM O_Grade AS temp3
WHERE temp3.P_Grade = self.F_grade) = 'professor') OR (self.taxClass = 'tc3')))));

CREATE OR REPLACE VIEW tudOclInv5 AS
(SELECT * FROM O_Facility AS self
WHERE NOT (self.F_headOfFacility IN
  ((SELECT temp1.F_member
FROM AS_member_owner AS temp1
WHERE temp1.F_owner = self.P_Facility))));

CREATE OR REPLACE VIEW tudOclInv6 AS
(SELECT * FROM O_Paper AS self
WHERE NOT ((NOT ((self.purpose = 'Diplom') AND (((self.inProgress = 1) AND (1=1)) OR (NOT (self.inProgress = 1) AND NOT (1=1)))) OR NOT EXISTS((SELECT temp1.F_author
FROM AS_author_papers AS temp1
WHERE (temp1.F_papers = self.P_Paper AND NOT(EXISTS(
  SELECT temp2.P_Person FROM O_Student AS temp2
  WHERE temp2.P_Person = temp1.F_author))))))));

CREATE OR REPLACE VIEW tudOclInv7 AS
(SELECT * FROM O_Faculty AS self
WHERE NOT (((SELECT temp1.name
FROM O_Grade AS temp1
INNER JOIN (SELECT F_grade,P_Person FROM O_Person) AS temp2 ON temp1.P_Grade = temp2.F_grade
WHERE temp2.P_Person = self.F_headOfFacility) = 'professor')));

CREATE OR REPLACE VIEW tudOclInv8 AS
(SELECT * FROM O_Grade AS self
WHERE NOT (self.name IN
  ('none'
UNION
'diploma'
UNION
'doctor'
UNION
'professor')));

CREATE OR REPLACE VIEW tudOclInv9_1 AS
(SELECT * FROM O_Employee AS self
WHERE NOT ((NOT ((SELECT temp1.name
FROM O_Grade AS temp1
WHERE temp1.P_Grade = self.F_grade) = 'doctor') OR ((SELECT CASE
  WHEN COUNT(temp2.P_Paper) IS NULL THEN 0
  ELSE COUNT(temp2.P_Paper)
END
FROM O_Paper AS temp2
INNER JOIN (SELECT F_papers,F_author FROM AS_author_papers) AS temp3 ON temp2.P_Paper = temp3.F_papers
WHERE (temp3.F_author = self.P_Person AND (temp2.purpose = 'Dissertation'))) = 1))));

CREATE OR REPLACE VIEW tudOclInv9_2 AS
(SELECT * FROM O_Employee AS self
WHERE NOT ((NOT ((SELECT temp1.name
FROM O_Grade AS temp1
WHERE temp1.P_Grade = self.F_grade) = 'doctor') OR ((SELECT CASE
  WHEN COUNT(temp2.P_Paper) IS NULL THEN 0
  ELSE COUNT(temp2.P_Paper)
END
FROM O_Paper AS temp2
INNER JOIN (SELECT F_papers,F_author FROM AS_author_papers) AS temp3 ON temp2.P_Paper = temp3.F_papers
WHERE (temp3.F_author = self.P_Person AND NOT((temp2.purpose = 'Dissertation')))) > 0))));

CREATE OR REPLACE VIEW tudOclInv10_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.F_papers) IS NULL THEN 0
  ELSE COUNT(temp1.F_papers)
END
FROM AS_author_papers AS temp1
WHERE (temp1.F_author = self.P_Person AND (temp1.F_papers = self.F_currentPaper))) = 1)));

CREATE OR REPLACE VIEW tudOclInv10_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT (self.F_currentPaper IN
  ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))));

CREATE OR REPLACE VIEW tudOclInv10_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT (NOT(self.F_currentPaper NOT IN
  ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_4 AS
(SELECT * FROM O_Student AS self
WHERE NOT (NOT EXISTS (
  ((SELECT temp2.F_papers
FROM AS_author_papers AS temp2
WHERE temp2.F_author = self.P_Person))
  EXCEPT
  ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_5 AS
(SELECT * FROM O_Student AS self
WHERE NOT (NOT(NOT EXISTS (
  ((SELECT temp2.F_papers
FROM AS_author_papers AS temp2
WHERE temp2.F_author = self.P_Person))
  INTERSECT
  ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_6 AS
(SELECT * FROM O_Student AS self
WHERE NOT (NOT(NOT EXISTS ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person)))));

CREATE OR REPLACE VIEW tudOclInv10_7 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))));

CREATE OR REPLACE VIEW tudOclInv10_8 AS
(SELECT * FROM O_Student AS self
WHERE NOT (((SELECT CASE
  WHEN COUNT(temp1.F_papers) IS NULL THEN 0
  ELSE COUNT(temp1.F_papers)
END
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person) > 0)));

CREATE OR REPLACE VIEW tudOclInv10_9 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((CASE
  WHEN SUM(self.salaries) IS NULL THEN 0
  ELSE SUM(self.salaries)
END = 300)));

CREATE OR REPLACE VIEW tudOclInv11_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp2.F_papers
FROM AS_author_papers AS temp2
WHERE temp2.F_author = self.F_supervisor) INTERSECT
  (SELECT temp1.F_papers
  FROM AS_author_papers AS temp1
  WHERE temp1.F_author = self.P_Person)))));

CREATE OR REPLACE VIEW tudOclInv11_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person) UNION
  (self.F_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person) EXCEPT
  (self.F_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_4 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person) UNION
  (SELECT temp2.F_papers
  FROM AS_author_papers AS temp2
  WHERE temp2.F_author = self.F_supervisor)))));

CREATE OR REPLACE VIEW tudOclInv12_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))
  UNION
  (SELECT temp1.F_papers, (SELECT MAX(SEQNO) FROM ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))) AS SEQNO
   FROM (SELECT temp2.F_papers
FROM AS_author_papers AS temp2
WHERE temp2.F_author = self.F_supervisor)))));

CREATE OR REPLACE VIEW tudOclInv12_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))
  UNION
  (SELECT self.F_currentPaper, ((SELECT MAX(SEQNO) FROM ((SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person))) + 1) AS SEQNO))));

CREATE OR REPLACE VIEW tudOclInv12_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT (EXISTS (SELECT self.F_currentPaper,
  (SELECT COUNT(*)+1 FROM (
    SELECT self.F_currentPaper, SEQNO
    FROM self.F_currentPaper
    WHERE NOT (P_Paper = self.F_currentPaper)
  ) WHERE SEQNO < s.SEQNO) AS SEQNO
  FROM (
    SELECT self.F_currentPaper, SEQNO
    FROM (SELECT temp1.F_papers
FROM AS_author_papers AS temp1
WHERE temp1.F_author = self.P_Person)
    WHERE NOT (P_Paper = self.F_currentPaper)
  ))));

CREATE OR REPLACE VIEW tudOclInv13_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((LENGTH(self.firstName) > 0)));

CREATE OR REPLACE VIEW tudOclInv13_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((LENGTH(self.firstName || self.lastName) = (LENGTH(self.firstName) + LENGTH(self.lastName)))));

CREATE OR REPLACE VIEW tudOclInv13_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((LENGTH(UPPER(self.firstName)) = LENGTH(self.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_4 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((LENGTH(LOWER(self.firstName)) = LENGTH(self.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_5 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((LENGTH(SUBSTRING(self.firstName, 1, 3 - 1 + 1)) = 3)));

CREATE OR REPLACE VIEW tudOclInv14_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((ABS(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((FLOOR(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((ROUND(self.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_4 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((CASE
  WHEN self.age > 1000 THEN self.age
  ELSE 1000
END = 1000)));

CREATE OR REPLACE VIEW tudOclInv14_5 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((CASE
  WHEN self.age < -1 THEN self.age
  ELSE -1
END = -1)));

CREATE OR REPLACE VIEW tudOclInv14_6 AS
(SELECT * FROM O_Student AS self
WHERE NOT (((self.age / 1000) < 1)));

CREATE OR REPLACE VIEW tudOclInv14_7 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.age - ((self.age / 1000) * 1000) = self.age)));

CREATE OR REPLACE VIEW tudOclInv15_1 AS
(SELECT * FROM O_Student AS self
WHERE NOT (('x' < 'y')));

CREATE OR REPLACE VIEW tudOclInv15_2 AS
(SELECT * FROM O_Student AS self
WHERE NOT (('y' <= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_3 AS
(SELECT * FROM O_Student AS self
WHERE NOT (('z' >= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_4 AS
(SELECT * FROM O_Student AS self
WHERE NOT (('z' > 'y')));

CREATE OR REPLACE VIEW tudOclInv15_5 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName < 'y')));

CREATE OR REPLACE VIEW tudOclInv15_6 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName <= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_7 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName >= 'y')));

CREATE OR REPLACE VIEW tudOclInv15_8 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName > 'y')));

CREATE OR REPLACE VIEW tudOclInv15_9 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName < self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_10 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName <= self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_11 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName >= self.lastName)));

CREATE OR REPLACE VIEW tudOclInv15_12 AS
(SELECT * FROM O_Student AS self
WHERE NOT ((self.firstName > self.lastName)));