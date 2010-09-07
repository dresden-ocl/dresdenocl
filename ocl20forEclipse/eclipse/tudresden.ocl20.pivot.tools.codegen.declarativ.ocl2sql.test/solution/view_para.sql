CREATE OR REPLACE VIEW tudOclInv1 AS
(SELECT * FROM O_Person AS SELF
WHERE NOT (((SELECT value FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person
 WHERE P_Person IN (SELECT F_supervisor FROM O_Person WHERE P_Person = SELF.P_Person))) > (SELECT value FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)))));

CREATE OR REPLACE VIEW tudOclInv2 AS
(SELECT * FROM O_Faculty AS SELF
WHERE NOT (((SELECT
  CASE
    WHEN COUNT(P_Facility) IS NULL THEN 0
    ELSE COUNT(P_Facility)
  END
  FROM (O_Facility)
  WHERE P_Facility IN (SELECT P_Facility FROM O_Facility WHERE F_superFacility = SELF.P_Facility))   >= 2)));

CREATE OR REPLACE VIEW tudOclInv3 AS
(SELECT * FROM O_Faculty AS SELF
WHERE NOT (NOT EXISTS (
  SELECT P_Facility FROM (SELECT P_Facility FROM O_Facility WHERE F_superFacility = SELF.P_Facility)
  WHERE P_Facility IN (
    SELECT P_Facility FROM O_Facility AS ALIAS2
    WHERE NOT exists (
  SELECT P_Facility FROM O_Institute
  WHERE P_Facility = ALIAS2.P_Facility)
  )
)));

CREATE OR REPLACE VIEW tudOclInv4 AS
(SELECT * FROM O_Employee AS SELF
WHERE NOT ((((NOT ((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)) = 'diploma') OR (SELF.taxClass = 'tc1')) AND (NOT ((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)) = 'doctor') OR (SELF.taxClass = 'tc2'))) AND (NOT ((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)) = 'professor') OR (SELF.taxClass = 'tc3')))));

CREATE OR REPLACE VIEW tudOclInv5 AS
(SELECT * FROM O_Facility AS SELF
WHERE NOT ((SELECT F_headOfFacility FROM O_Facility WHERE P_Facility = SELF.P_Facility) IN
  ((SELECT P_Person FROM O_Person
 WHERE P_Person IN (SELECT F_member FROM AS_member_owner
 WHERE F_owner IN (SELECT P_Facility FROM O_Facility WHERE P_Facility = SELF.P_Facility))))));

CREATE OR REPLACE VIEW tudOclInv6 AS
(SELECT * FROM O_Paper AS SELF
WHERE NOT ((NOT ((SELF.purpose = 'Diplom') AND (((SELF.inProgress = 1) AND (1=1)) OR (NOT (SELF.inProgress = 1) AND NOT (1=1)))) OR NOT EXISTS (
  SELECT P_Person FROM (SELECT P_Person FROM O_Person
 WHERE P_Person IN (SELECT F_author FROM AS_author_papers
 WHERE F_papers IN (SELECT P_Paper FROM O_Paper WHERE P_Paper = SELF.P_Paper)))
  WHERE P_Person IN (
    SELECT P_Person FROM O_Person AS ALIAS3
    WHERE NOT exists (
  SELECT P_Person FROM O_Student
  WHERE P_Person = ALIAS3.P_Person)
  )
))));

CREATE OR REPLACE VIEW tudOclInv7 AS
(SELECT * FROM O_Faculty AS SELF
WHERE NOT (((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person
 WHERE P_Person IN (SELECT F_headOfFacility FROM O_Facility WHERE P_Facility = SELF.P_Facility))) = 'professor')));

CREATE OR REPLACE VIEW tudOclInv8 AS
(SELECT * FROM O_Grade AS SELF
WHERE NOT (SELF.name IN
  ('none'
UNION
'diploma'
UNION
'doctor'
UNION
'professor')));

CREATE OR REPLACE VIEW tudOclInv9_1 AS
(SELECT * FROM O_Employee AS SELF
WHERE NOT ((NOT ((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)) = 'doctor') OR ((SELECT
  CASE
    WHEN COUNT(P_Paper) IS NULL THEN 0
    ELSE COUNT(P_Paper)
  END
  FROM (O_Paper)
  WHERE P_Paper IN (SELECT P_Paper FROM (SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Employee WHERE P_Person = SELF.P_Person)))
  WHERE P_Paper IN (
    SELECT P_Paper FROM O_Paper AS ALIAS4
    WHERE ((ALIAS4.purpose = 'Dissertation'))
  )
))   = 1))));

CREATE OR REPLACE VIEW tudOclInv9_2 AS
(SELECT * FROM O_Employee AS SELF
WHERE NOT ((NOT ((SELECT name FROM O_Grade
 WHERE P_Grade IN (SELECT F_grade FROM O_Person WHERE P_Person = SELF.P_Person)) = 'doctor') OR ((SELECT
  CASE
    WHEN COUNT(P_Paper) IS NULL THEN 0
    ELSE COUNT(P_Paper)
  END
  FROM (O_Paper)
  WHERE P_Paper IN (SELECT P_Paper FROM (SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Employee WHERE P_Person = SELF.P_Person)))
  WHERE P_Paper IN (
    SELECT P_Paper FROM O_Paper AS ALIAS5
    WHERE NOT ((ALIAS5.purpose = 'Dissertation'))
  )
))   > 0))));

CREATE OR REPLACE VIEW tudOclInv10_1 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (((SELECT
  CASE 
    WHEN COUNT(P_Paper) IS NULL THEN 0
    ELSE COUNT(P_Paper)
  END
  FROM (O_Paper)
  WHERE P_Paper IN (SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person)))
  AND P_Paper = (SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person)) = 1)));

CREATE OR REPLACE VIEW tudOclInv10_2 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person) IN
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_3 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((NOT ((SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person) NOT IN
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))))));

CREATE OR REPLACE VIEW tudOclInv10_4 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (NOT EXISTS (
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))
  EXCEPT
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person)))))));

CREATE OR REPLACE VIEW tudOclInv10_5 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((NOT (NOT EXISTS (
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))
  INTERSECT
  ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person)))))))));

CREATE OR REPLACE VIEW tudOclInv10_6 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((NOT (NOT EXISTS ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))))));

CREATE OR REPLACE VIEW tudOclInv10_7 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS ((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))));

CREATE OR REPLACE VIEW tudOclInv10_8 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (((SELECT
  CASE
    WHEN COUNT(P_Paper) IS NULL THEN 0
    ELSE COUNT(P_Paper)
  END
  FROM (O_Paper)
  WHERE P_Paper IN (SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))))   > 0)));

CREATE OR REPLACE VIEW tudOclInv10_9 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (((SELECT
  CASE 
    WHEN SUM(salaries) IS NULL THEN 0
    ELSE SUM(salaries)
  END
  FROM O_Student
  WHERE salaries IN (SELF.salaries)) = 300)));

CREATE OR REPLACE VIEW tudOclInv11_1 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Person
 WHERE P_Person IN (SELECT F_supervisor FROM O_Student WHERE P_Person = SELF.P_Person)))) INTERSECT
  (SELECT P_Paper FROM O_Paper
   WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
   WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person)))))));

CREATE OR REPLACE VIEW tudOclInv11_2 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))) UNION
  ((SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person))))));

CREATE OR REPLACE VIEW tudOclInv11_3 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))) EXCEPT
  ((SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person))))));

CREATE OR REPLACE VIEW tudOclInv11_4 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (((SELECT P_Paper FROM O_Paper
 WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
 WHERE F_author IN (SELECT P_Person FROM O_Student WHERE P_Person = SELF.P_Person))) UNION
  (SELECT P_Paper FROM O_Paper
   WHERE P_Paper IN (SELECT F_papers FROM AS_author_papers
   WHERE F_author IN (SELECT P_Person FROM O_Person
   WHERE P_Person IN (SELECT F_supervisor FROM O_Student WHERE P_Person = SELF.P_Person))))))));

CREATE OR REPLACE VIEW tudOclInv12_1 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (()
  UNION
  (SELECT P_Paper, (SELECT MAX(SEQNO) FROM ()) + SEQNO
   FROM ))));

CREATE OR REPLACE VIEW tudOclInv12_2 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (()
  UNION
  (SELECT (SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person), ((SELECT MAX(SEQNO) FROM ()) + 1) AS SEQNO))));

CREATE OR REPLACE VIEW tudOclInv12_3 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (EXISTS (SELECT P_Paper,
  (SELECT COUNT(*)+1 FROM (
    SELECT P_Paper, SEQNO
    FROM 
    WHERE NOT (P_Paper = (SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person))
  ) WHERE SEQNO < s.SEQNO) AS SEQNO
  FROM (
    SELECT P_Paper, SEQNO
    FROM 
    WHERE NOT (P_Paper = (SELECT F_currentPaper FROM O_Person WHERE P_Person = SELF.P_Person))
  ))));

CREATE OR REPLACE VIEW tudOclInv13_1 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((LENGTH (SELF.firstName) > 0)));

CREATE OR REPLACE VIEW tudOclInv13_2 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((LENGTH (SELF.firstName || SELF.lastName) = (LENGTH (SELF.firstName) + LENGTH (SELF.lastName)))));

CREATE OR REPLACE VIEW tudOclInv13_3 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((LENGTH (UPPER (SELF.firstName)) = LENGTH (SELF.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_4 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((LENGTH (LOWER (SELF.firstName)) = LENGTH (SELF.firstName))));

CREATE OR REPLACE VIEW tudOclInv13_5 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((LENGTH (SUBSTRING(SELF.firstName, 1, 3 - 1 + 1)) = 3)));

CREATE OR REPLACE VIEW tudOclInv14_1 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((ABS (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_2 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((FLOOR (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_3 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((ROUND (SELF.age) > 0)));

CREATE OR REPLACE VIEW tudOclInv14_4 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((CASE
  WHEN SELF.age > 1000 THEN SELF.age
  ELSE 1000
END = 1000)));

CREATE OR REPLACE VIEW tudOclInv14_5 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((CASE 
  WHEN SELF.age < -1 THEN SELF.age
  ELSE -1
END = -1)));

CREATE OR REPLACE VIEW tudOclInv14_6 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT (((SELF.age / 1000) < 1)));

CREATE OR REPLACE VIEW tudOclInv14_7 AS
(SELECT * FROM O_Student AS SELF
WHERE NOT ((SELF.age - ((SELF.age / 1000) * 1000) = SELF.age)));
