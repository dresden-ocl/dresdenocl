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
-- Expression: def: oclinvp11: Integer = Book.allInstances().author.books->collect(x|x.title)->size()
CREATE OR REPLACE VIEW oclinvp11 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp12: Integer = Book.allInstances().author.books->collect(x|x.title <> 'Hobbit')->size()
CREATE OR REPLACE VIEW oclinvp12 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp13: Integer = Book.allInstances().author.books->collect(x|x.author.books)->size()
CREATE OR REPLACE VIEW oclinvp13 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp14: Integer = Book.allInstances().author.books->collect(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp14 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: inv oclinvp15: Book.allInstances().author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp15 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS4
    WHERE NOT (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: def: oclinvp16: Integer = Book.allInstances().author.books->select(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp16 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))
  AND PK_Book IN (
    SELECT DISTINCT PK_Book FROM OV_Book AS ALIAS4
    WHERE (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))))
  )
)) );

-- Context: Book
-- Expression: def: oclinvp17: Integer = Book.allInstances().author.books->collect(x|x.author.books.title)->size()
CREATE OR REPLACE VIEW oclinvp17 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp18: Integer = Book.allInstances().author.books->collect(x|x.author.books.title->size())->sum()
CREATE OR REPLACE VIEW oclinvp18 AS
(SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ));

-- Context: Book
-- Expression: inv oclinvp19: Book.allInstances().author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp19 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS4
    WHERE NOT ('Hobbit' NOT IN
  (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS4.PK_Book)))))) ))
  )
)));

-- Context: Book
-- Expression: def: oclinvp21: Integer = Book.allInstances().author.books.author.books->collect(x|x.title)->size()
CREATE OR REPLACE VIEW oclinvp21 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp22: Integer = Book.allInstances().author.books.author.books->collect(x|x.title <> 'Hobbit')->size()
CREATE OR REPLACE VIEW oclinvp22 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp23: Integer = Book.allInstances().author.books.author.books->collect(x|x.author.books)->size()
CREATE OR REPLACE VIEW oclinvp23 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp24: Integer = Book.allInstances().author.books.author.books->collect(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp24 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: inv oclinvp25: Book.allInstances().author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp25 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS6
    WHERE NOT (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: def: oclinvp26: Integer = Book.allInstances().author.books.author.books->select(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp26 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))
  AND PK_Book IN (
    SELECT DISTINCT PK_Book FROM OV_Book AS ALIAS6
    WHERE (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))))
  )
)) );

-- Context: Book
-- Expression: def: oclinvp27: Integer = Book.allInstances().author.books.author.books->collect(x|x.author.books.title)->size()
CREATE OR REPLACE VIEW oclinvp27 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp28: Integer = Book.allInstances().author.books.author.books->collect(x|x.author.books.title->size())->sum()
CREATE OR REPLACE VIEW oclinvp28 AS
(SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ));

-- Context: Book
-- Expression: inv oclinvp29: Book.allInstances().author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp29 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS6
    WHERE NOT ('Hobbit' NOT IN
  (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS6.PK_Book)))))) ))
  )
)));

-- Context: Book
-- Expression: def: oclinvp31: Integer = Book.allInstances().author.books.author.books.author.books->collect(x|x.title)->size()
CREATE OR REPLACE VIEW oclinvp31 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp32: Integer = Book.allInstances().author.books.author.books.author.books->collect(x|x.title <> 'Hobbit')->size()
CREATE OR REPLACE VIEW oclinvp32 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp33: Integer = Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books)->size()
CREATE OR REPLACE VIEW oclinvp33 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp34: Integer = Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp34 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: inv oclinvp35: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp35 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS8
    WHERE NOT (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))))
  )
)));

-- Context: Book
-- Expression: def: oclinvp36: Integer = Book.allInstances().author.books.author.books.author.books->select(x|x.author.books->includes(x))->size()
CREATE OR REPLACE VIEW oclinvp36 AS
(SELECT
  IF(COUNT(PK_Book) IS NULL,0,COUNT(PK_Book)) AS 'COUNT(PK_Book)'
  FROM (OV_Book)
  WHERE PK_Book IN ((SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))
  AND PK_Book IN (
    SELECT DISTINCT PK_Book FROM OV_Book AS ALIAS8
    WHERE (PK_Book IN
  (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))))
  )
)) );

-- Context: Book
-- Expression: def: oclinvp37: Integer =  Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title)->size()
CREATE OR REPLACE VIEW oclinvp37 AS
(SELECT
  IF(COUNT(title) IS NULL,0,COUNT(title)) AS 'COUNT(title)'
  FROM (OV_Book)
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ) );

-- Context: Book
-- Expression: def: oclinvp38: Integer = Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title->size())->sum()
CREATE OR REPLACE VIEW oclinvp38 AS
(SELECT
  IF(SUM(title) IS NULL,0,SUM(title)) AS 'SUM(title)'
  FROM OV_Book
  WHERE title IN (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book )))))) ));

-- Context: Book
-- Expression: inv oclinvp39: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp39 AS
(SELECT * FROM Book AS SELF
WHERE NOT (NOT EXISTS (
  SELECT DISTINCT PK_Book FROM OV_Book
   WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
   WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
   WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
   WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = SELF.PK_Book)))))
  AND PK_Book IN (
    SELECT PK_Book FROM OV_Book AS ALIAS8
    WHERE NOT ('Hobbit' NOT IN
  (SELECT DISTINCT title FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT PK_Book FROM OV_Book
 WHERE PK_Book IN (SELECT DISTINCT FK_books FROM ASS_author_books
 WHERE FK_author IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT PK_Writer FROM OV_Writer
 WHERE PK_Writer IN (SELECT DISTINCT FK_author FROM ASS_author_books
 WHERE FK_books IN (SELECT DISTINCT PK_Book FROM OV_Book WHERE PK_Book = ALIAS8.PK_Book)))))) ))
  )
)));