CREATE OR REPLACE VIEW tudOclInv1 AS
(SELECT * FROM OV_Person AS SELF
WHERE NOT (((SELECT value FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person
 WHERE PK_Person IN (SELECT FK_supervisor FROM OV_Person WHERE PK_Person = SELF.PK_Person))) > (SELECT value FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)))));

CREATE OR REPLACE VIEW tudOclInv2 AS
(SELECT * FROM OV_Faculty AS SELF
WHERE NOT (((SELECT
  CASE
    WHEN COUNT(PK_Facility) IS NULL THEN 0
    ELSE COUNT(PK_Facility)
  END
  FROM (OV_Facility)
  WHERE PK_Facility IN (SELECT PK_Facility FROM OV_Facility WHERE FK_superFacility = SELF.PK_Facility))   >= 2)));

CREATE OR REPLACE VIEW tudOclInv3 AS
(SELECT * FROM OV_Faculty AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Facility FROM (SELECT PK_Facility FROM OV_Facility WHERE FK_superFacility = SELF.PK_Facility)
  WHERE PK_Facility IN (
    SELECT PK_Facility FROM OV_Facility AS ALIAS2
    WHERE NOT exists (
  SELECT PK_Facility FROM OV_Institute
  WHERE PK_Facility = ALIAS2.PK_Facility)
  )
)));

CREATE OR REPLACE VIEW tudOclInv4 AS
(SELECT * FROM OV_Employee AS SELF
WHERE NOT ((((NOT ((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)) = 'diploma') OR (SELF.taxClass = 'tc1')) AND (NOT ((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)) = 'doctor') OR (SELF.taxClass = 'tc2'))) AND (NOT ((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)) = 'professor') OR (SELF.taxClass = 'tc3')))));

CREATE OR REPLACE VIEW tudOclInv5 AS
(SELECT * FROM OV_Facility AS SELF
WHERE NOT ((SELECT FK_headOfFacility FROM OV_Facility WHERE PK_Facility = SELF.PK_Facility) IN
  ((SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_member FROM ASS_member_owner
 WHERE FK_owner IN (SELECT PK_Facility FROM OV_Facility WHERE PK_Facility = SELF.PK_Facility))))));

CREATE OR REPLACE VIEW tudOclInv6 AS
(SELECT * FROM OV_Paper AS SELF
WHERE NOT ((NOT ((SELF.purpose = 'Diplom') AND (((SELF.inProgress = 1) AND (1=1)) OR (NOT (SELF.inProgress = 1) AND NOT (1=1)))) OR NOT EXISTS (
  SELECT PK_Person FROM (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_author FROM ASS_author_papers
 WHERE FK_papers IN (SELECT PK_Paper FROM OV_Paper WHERE PK_Paper = SELF.PK_Paper)))
  WHERE PK_Person IN (
    SELECT PK_Person FROM OV_Person AS ALIAS2
    WHERE NOT exists (
  SELECT PK_Person FROM OV_Student
  WHERE PK_Person = ALIAS2.PK_Person)
  )
))));

CREATE OR REPLACE VIEW tudOclInv7 AS
(SELECT * FROM OV_Faculty AS SELF
WHERE NOT (((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person
 WHERE PK_Person IN (SELECT FK_headOfFacility FROM OV_Facility WHERE PK_Facility = SELF.PK_Facility))) = 'professor')));

CREATE OR REPLACE VIEW tudOclInv8 AS
(SELECT * FROM OV_Grade AS SELF
WHERE NOT (SELF.name IN
  ('none'
UNION
'diploma'
UNION
'doctor'
UNION
'professor')));

CREATE OR REPLACE VIEW tudOclInv9_1 AS
(SELECT * FROM OV_Employee AS SELF
WHERE NOT ((NOT ((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)) = 'doctor') OR ((SELECT
  CASE
    WHEN COUNT(PK_Paper) IS NULL THEN 0
    ELSE COUNT(PK_Paper)
  END
  FROM (OV_Paper)
  WHERE PK_Paper IN (SELECT PK_Paper FROM (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Employee WHERE PK_Person = SELF.PK_Person)))
  WHERE PK_Paper IN (
    SELECT PK_Paper FROM OV_Paper AS ALIAS2
    WHERE ((ALIAS2.purpose = 'Dissertation'))
  )
))   = 1))));

CREATE OR REPLACE VIEW tudOclInv9_2 AS
(SELECT * FROM OV_Employee AS SELF
WHERE NOT ((NOT ((SELECT name FROM OV_Grade
 WHERE PK_Grade IN (SELECT FK_grade FROM OV_Person WHERE PK_Person = SELF.PK_Person)) = 'doctor') OR ((SELECT
  CASE
    WHEN COUNT(PK_Paper) IS NULL THEN 0
    ELSE COUNT(PK_Paper)
  END
  FROM (OV_Paper)
  WHERE PK_Paper IN (SELECT PK_Paper FROM (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Employee WHERE PK_Person = SELF.PK_Person)))
  WHERE PK_Paper IN (
    SELECT PK_Paper FROM OV_Paper AS ALIAS2
    WHERE NOT ((ALIAS2.purpose = 'Dissertation'))
  )
))   > 0))));

CREATE OR REPLACE VIEW tudOclInv10_1 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (((SELECT
  CASE 
    WHEN COUNT(PK_Paper) IS NULL THEN 0
    ELSE COUNT(PK_Paper)
  END
  FROM (OV_Paper)
  WHERE PK_Paper IN (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))
  AND PK_Paper = SELF.FK_currentPaper) = 1)));

CREATE OR REPLACE VIEW tudOclInv10_2 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (SELF.FK_currentPaper IN
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_3 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((NOT (SELF.FK_currentPaper NOT IN
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))))));

CREATE OR REPLACE VIEW tudOclInv10_4 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (NOT EXISTS (
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))
  EXCEPT
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))))));

CREATE OR REPLACE VIEW tudOclInv10_5 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((NOT (NOT EXISTS (
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))
  INTERSECT
  ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))))))));

CREATE OR REPLACE VIEW tudOclInv10_6 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((NOT (NOT EXISTS ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))))));

CREATE OR REPLACE VIEW tudOclInv10_7 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_8 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (((SELECT
  CASE
    WHEN COUNT(PK_Paper) IS NULL THEN 0
    ELSE COUNT(PK_Paper)
  END
  FROM (OV_Paper)
  WHERE PK_Paper IN (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))   > 0)));

CREATE OR REPLACE VIEW tudOclInv10_9 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (((SELECT
  CASE 
    WHEN SUM(salaries) IS NULL THEN 0
    ELSE SUM(salaries)
  END
  FROM OV_Student
  WHERE salaries IN (SELF.salaries)) = 300)));

CREATE OR REPLACE VIEW tudOclInv11_1 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_supervisor FROM OV_Student WHERE PK_Person = SELF.PK_Person)))) INTERSECT
  (SELECT PK_Paper FROM OV_Paper
   WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
   WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))))));

CREATE OR REPLACE VIEW tudOclInv11_2 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))) UNION
  (SELF.FK_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_3 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))) EXCEPT
  (SELF.FK_currentPaper)))));

CREATE OR REPLACE VIEW tudOclInv11_4 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))) UNION
  (SELECT PK_Paper FROM OV_Paper
   WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
   WHERE FK_author IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT FK_supervisor FROM OV_Student WHERE PK_Person = SELF.PK_Person))))))));

CREATE OR REPLACE VIEW tudOclInv12_1 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))
  UNION
  (SELECT PK_Paper, (SELECT MAX(SEQNO) FROM ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))) AS SEQNO
   FROM (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_supervisor FROM OV_Student WHERE PK_Person = SELF.PK_Person))))))));

CREATE OR REPLACE VIEW tudOclInv12_2 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))
  UNION
  (SELECT SELF.FK_currentPaper, ((SELECT MAX(SEQNO) FROM ((SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person))))) + 1) AS SEQNO))));

CREATE OR REPLACE VIEW tudOclInv12_3 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (EXISTS (SELECT PK_Paper,
  (SELECT COUNT(*)+1 FROM (
    SELECT PK_Paper, SEQNO
    FROM (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))
    WHERE NOT (PK_Paper = SELF.FK_currentPaper)
  ) WHERE SEQNO < s.SEQNO) AS SEQNO
  FROM (
    SELECT PK_Paper, SEQNO
    FROM (SELECT PK_Paper FROM OV_Paper
 WHERE PK_Paper IN (SELECT FK_papers FROM ASS_author_papers
 WHERE FK_author IN (SELECT PK_Person FROM OV_Student WHERE PK_Person = SELF.PK_Person)))
    WHERE NOT (PK_Paper = SELF.FK_currentPaper)
  ))));

CREATE OR REPLACE VIEW tudOclInv13_1 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((LENGTH (SELF.firstName) > 0)));

CREATE OR REPLACE VIEW tudOclInv13_2 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((LENGTH (SELF.firstName || SELF.lastName) = (LENGTH (SELF.firstName) + LENGTH (SELF.lastName)))));

CREATE OR REPLACE VIEW tudOclInv13_3 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((LENGTH (UPPER (SELF.firstName)) = LENGTH (SELF.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_4 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((LENGTH (LOWER (SELF.firstName)) = LENGTH (SELF.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_5 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((LENGTH (SUBSTRING(SELF.firstName, 1, 3 - 1 + 1)) = 3)));

CREATE OR REPLACE VIEW tudOclInv14_1 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((ABS (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_2 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((FLOOR (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_3 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((ROUND (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_4 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((CASE
  WHEN SELF.age > 1000 THEN SELF.age
  ELSE 1000
END = 1000)));

CREATE OR REPLACE VIEW tudOclInv14_5 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((CASE 
  WHEN SELF.age < -1 THEN SELF.age
  ELSE -1
END = -1)));

CREATE OR REPLACE VIEW tudOclInv14_6 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT (((SELF.age / 1000) < 1)));

CREATE OR REPLACE VIEW tudOclInv14_7 AS
(SELECT * FROM OV_Student AS SELF
WHERE NOT ((SELF.age - ((SELF.age / 1000) * 1000) = SELF.age)));
