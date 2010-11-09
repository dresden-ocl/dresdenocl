-- Generated from OCL2SQL generator(Dresden OCL)
-- Generation time: 2010-11-09-19-06
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
-- Expression: inv oclinvp11: Book.allInstances().author.books->collect(x|x.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp11 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp12: Book.allInstances().author.books->collect(x|x.title <> 'Hobbit')->size() = 1
CREATE OR REPLACE VIEW oclinvp12 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.title = 'Hobbit')),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp13: Book.allInstances().author.books->collect(x|x.author.books)->size() = 1
CREATE OR REPLACE VIEW oclinvp13 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp14: Book.allInstances().author.books->collect(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp14 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp3.FK_books IN
  (temp1.FK_books)),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp15: Book.allInstances().author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp15 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
WHERE NOT(temp1.FK_books IN
  ((SELECT temp3.FK_books
FROM ASS_author_books AS temp3
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
 WHERE temp4.FK_books = temp1.FK_books)))))));

-- Context: Book
-- Expression: inv oclinvp16: Book.allInstances().author.books->select(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp16 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
WHERE temp1.FK_books IN
  ((SELECT temp3.FK_books
FROM ASS_author_books AS temp3
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
 WHERE temp4.FK_books = temp1.FK_books))) = 1)));

-- Context: Book
-- Expression: inv oclinvp17: Book.allInstances().author.books->collect(x|x.author.books.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp17 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp18: Book.allInstances().author.books->collect(x|x.author.books.title->size())->sum() = 1
CREATE OR REPLACE VIEW oclinvp18 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(SUM(IF(temp1.title IS NULL,NULL,1)),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp19: Book.allInstances().author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp19 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
WHERE NOT('Hobbit' NOT IN
  ((SELECT temp3.title
FROM OV_Book AS temp3
INNER JOIN ASS_author_books AS temp4 ON temp3.PK_Book = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
 WHERE temp5.FK_books = temp1.FK_books)))))));

-- Context: Book
-- Expression: inv oclinvp21: Book.allInstances().author.books.author.books->collect(x|x.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp21 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp22: Book.allInstances().author.books.author.books->collect(x|x.title <> 'Hobbit')->size() = 1
CREATE OR REPLACE VIEW oclinvp22 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.title = 'Hobbit')),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp23: Book.allInstances().author.books.author.books->collect(x|x.author.books)->size() = 1
CREATE OR REPLACE VIEW oclinvp23 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp24: Book.allInstances().author.books.author.books->collect(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp24 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp3.FK_books IN
  (temp1.FK_books)),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp25: Book.allInstances().author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp25 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
WHERE NOT(temp1.FK_books IN
  ((SELECT temp5.FK_books
FROM ASS_author_books AS temp5
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
 WHERE temp6.FK_books = temp1.FK_books)))))));

-- Context: Book
-- Expression: inv oclinvp26: Book.allInstances().author.books.author.books->select(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp26 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
WHERE temp1.FK_books IN
  ((SELECT temp5.FK_books
FROM ASS_author_books AS temp5
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
 WHERE temp6.FK_books = temp1.FK_books))) = 1)));

-- Context: Book
-- Expression: inv oclinvp27: Book.allInstances().author.books.author.books->collect(x|x.author.books.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp27 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp28: Book.allInstances().author.books.author.books->collect(x|x.author.books.title->size())->sum() = 1
CREATE OR REPLACE VIEW oclinvp28 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(SUM(IF(temp1.title IS NULL,NULL,1)),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp29: Book.allInstances().author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp29 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
WHERE NOT('Hobbit' NOT IN
  ((SELECT temp5.title
FROM OV_Book AS temp5
INNER JOIN ASS_author_books AS temp6 ON temp5.PK_Book = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
 WHERE temp7.FK_books = temp1.FK_books)))))));

-- Context: Book
-- Expression: inv oclinvp31: Book.allInstances().author.books.author.books.author.books->collect(x|x.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp31 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp32: Book.allInstances().author.books.author.books.author.books->collect(x|x.title <> 'Hobbit')->size() = 1
CREATE OR REPLACE VIEW oclinvp32 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.title = 'Hobbit')),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp33: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books)->size() = 1
CREATE OR REPLACE VIEW oclinvp33 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_books = temp7.FK_books
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_author = temp8.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp34: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp34 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp3.FK_books IN
  (temp1.FK_books)),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_books = temp7.FK_books
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_author = temp8.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp35: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books->includes(x))
CREATE OR REPLACE VIEW oclinvp35 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
WHERE NOT(temp1.FK_books IN
  ((SELECT temp7.FK_books
FROM ASS_author_books AS temp7
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_author = temp8.FK_author
 WHERE temp8.FK_books = temp1.FK_books)))))));

-- Context: Book
-- Expression: inv oclinvp36: Book.allInstances().author.books.author.books.author.books->select(x|x.author.books->includes(x))->size() = 1
CREATE OR REPLACE VIEW oclinvp36 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_books),0)
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
WHERE temp1.FK_books IN
  ((SELECT temp7.FK_books
FROM ASS_author_books AS temp7
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_author = temp8.FK_author
 WHERE temp8.FK_books = temp1.FK_books))) = 1)));

-- Context: Book
-- Expression: inv oclinvp37: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title)->size() = 1
CREATE OR REPLACE VIEW oclinvp37 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.title),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_books = temp8.FK_books
INNER JOIN ASS_author_books AS temp9 ON temp8.FK_author = temp9.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp38: Book.allInstances().author.books.author.books.author.books->collect(x|x.author.books.title->size())->sum() = 1
CREATE OR REPLACE VIEW oclinvp38 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (((SELECT IFNULL(SUM(IF(temp1.title IS NULL,NULL,1)),0)
FROM OV_Book AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.PK_Book = temp2.FK_books
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_author = temp3.FK_author
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_books = temp4.FK_books
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_author = temp5.FK_author
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_books = temp6.FK_books
INNER JOIN ASS_author_books AS temp7 ON temp6.FK_author = temp7.FK_author
INNER JOIN ASS_author_books AS temp8 ON temp7.FK_books = temp8.FK_books
INNER JOIN ASS_author_books AS temp9 ON temp8.FK_author = temp9.FK_author
) = 1)));

-- Context: Book
-- Expression: inv oclinvp39: Book.allInstances().author.books.author.books.author.books->forAll(x|x.author.books.title->excludes('Hobbit'))
CREATE OR REPLACE VIEW oclinvp39 AS
(SELECT * FROM OV_Book AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_books
FROM ASS_author_books AS temp1
INNER JOIN ASS_author_books AS temp2 ON temp1.FK_author = temp2.FK_author
INNER JOIN ASS_author_books AS temp3 ON temp2.FK_books = temp3.FK_books
INNER JOIN ASS_author_books AS temp4 ON temp3.FK_author = temp4.FK_author
INNER JOIN ASS_author_books AS temp5 ON temp4.FK_books = temp5.FK_books
INNER JOIN ASS_author_books AS temp6 ON temp5.FK_author = temp6.FK_author
WHERE NOT('Hobbit' NOT IN
  ((SELECT temp7.title
FROM OV_Book AS temp7
INNER JOIN ASS_author_books AS temp8 ON temp7.PK_Book = temp8.FK_books
INNER JOIN ASS_author_books AS temp9 ON temp8.FK_author = temp9.FK_author
 WHERE temp9.FK_books = temp1.FK_books)))))));