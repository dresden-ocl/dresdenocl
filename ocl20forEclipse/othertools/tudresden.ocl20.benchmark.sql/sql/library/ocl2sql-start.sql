-- Generated from OCL2SQL generator(Dresden OCL2)
-- Generation time: 2010-09-21-12-46
-- Template: MySQL(SQL)
-- Modus: typed


CREATE TABLE `ASS_author_books` (
`FK_author` VARCHAR(255) REFERENCES T_Writer(PK_Writer) ,`FK_books` VARCHAR(255) REFERENCES T_Book(PK_Book) 
) TYPE = InnoDB;

CREATE TABLE `T_Book` (
`PK_Book` VARCHAR(255) PRIMARY KEY,`title` VARCHAR(255)
) TYPE = InnoDB;

CREATE TABLE `T_Writer` (
`PK_Writer` VARCHAR(255) PRIMARY KEY
) TYPE = InnoDB;

CREATE VIEW `OV_Book`
AS ( SELECT PK_Book,T_Book.title AS title
FROM T_Book
 );

CREATE VIEW `OV_Writer`
AS ( SELECT PK_Writer
FROM T_Writer
 );

ALTER TABLE `ASS_author_books` ADD CONSTRAINT `CONASS_author_booksFK_author`
FOREIGN KEY (`FK_author`) REFERENCES `T_Writer`(`PK_Writer`);

ALTER TABLE `ASS_author_books` ADD CONSTRAINT `CONASS_author_booksFK_books`
FOREIGN KEY (`FK_books`) REFERENCES `T_Book`(`PK_Book`);

-- Context: Book
-- Expression: inv oclinvp11: Book.allInstances().author.books->collect(x|x.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp11 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp12: Book.allInstances().author.books->collect(x|x.title <> 'Hobbit')->size() > 0
CREATE OR REPLACE VIEW oclinvp12 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp13: Book.allInstances().author.books->collect(x|x.author.books)->size() > 0
CREATE OR REPLACE VIEW oclinvp13 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp14: Book.allInstances().author.books->collect(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp14 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp15: Book.allInstances().author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp15 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS4
    WHERE NOT (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: inv oclinvp16: Book.allInstances().author.books->select(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp16 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS4
    WHERE (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))))
  )
)) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp17: Book.allInstances().author.books->collect(x|x.author.books.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp17 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp18: Book.allInstances().author.books->collect(x|x.author.books.title->size())->sum() > 0
CREATE OR REPLACE VIEW oclinvp18 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) )) > (0))));

-- Context: Book
-- Expression: inv oclinvp19: Book.allInstances().author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp19 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS4
    WHERE NOT ('Hobbit' NOT IN
  (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))) ))
  )
)));

-- Context: Book
-- Expression: inv oclinvp21: Book.allInstances().author.books.author.books->collect(x|x.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp21 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp22: Book.allInstances().author.books.author.books->collect(x|x.title <> 'Hobbit')->size() > 0
CREATE OR REPLACE VIEW oclinvp22 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp23: Book.allInstances().author.books.author.books->collect(x|x.author.books)->size() > 0
CREATE OR REPLACE VIEW oclinvp23 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp24: Book.allInstances().author.books.author.books->collect(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp24 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp25: Book.allInstances().author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp25 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS6
    WHERE NOT (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: inv oclinvp26: Book.allInstances().author.books.author.books->select(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp26 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS6
    WHERE (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))))
  )
)) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp27: Book.allInstances().author.books.author.books->collect(x|x.author.books.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp27 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp28: Book.allInstances().author.books.author.books->collect(x|x.author.books.title->size())->sum() > 0
CREATE OR REPLACE VIEW oclinvp28 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) )) > (0))));

-- Context: Book
-- Expression: inv oclinvp29: Book.allInstances().author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp29 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS6
    WHERE NOT ('Hobbit' NOT IN
  (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))) ))
  )
)));

-- Context: Book
-- Expression: inv oclinvp31: Book.allInstances().author.books.author.books.author.books->collect(x|x.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp31 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp32: Book.allInstances().author.books.author.books.author.books->collect(x|x.title <> 'Hobbit')->size() > 0
CREATE OR REPLACE VIEW oclinvp32 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp33: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books)->size() > 0
CREATE OR REPLACE VIEW oclinvp33 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp34: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp34 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp35: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp35 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS8
    WHERE NOT (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: inv oclinvp36: Book.allInstances().author.books.author.books.author.books->select(x|x.author.books->includes(x))->size() > 0
CREATE OR REPLACE VIEW oclinvp36 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS8
    WHERE (PK_Book IN
  (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))))
  )
)) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp37: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title)->size() > 0
CREATE OR REPLACE VIEW oclinvp37 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) ) ) > (0))));

-- Context: Book
-- Expression: inv oclinvp38: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title->size())->sum() > 0
CREATE OR REPLACE VIEW oclinvp38 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (((SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))) )) > (0))));

-- Context: Book
-- Expression: inv oclinvp39: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp39 AS
(SELECT * FROM OV_Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS8
    WHERE NOT ('Hobbit' NOT IN
  (SELECT title FROM OV_Book
 WHERE PK_Book IN (SELECT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))) ))
  )
)));