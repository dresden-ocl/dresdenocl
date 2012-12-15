-- Generated from OCL2SQL generator(Dresden OCL)
-- Generation time: 2010-11-08-09-29
-- Template: MySQL(SQL)
-- Modus: typed


CREATE TABLE `ASO_ownedCars_owner` (
`FK_ownedCars` VARCHAR(255) REFERENCES TO_Car(PK_Car) ,`FK_owner` VARCHAR(255) REFERENCES TO_Person(PK_Person) 
) TYPE = InnoDB;

CREATE TABLE `TO_Car` (
`PK_Car` VARCHAR(255) PRIMARY KEY,`color` VARCHAR(255),`model` VARCHAR(255)
) TYPE = InnoDB;

CREATE TABLE `TO_Person` (
`PK_Person` VARCHAR(255) PRIMARY KEY,`age` INT,`name` VARCHAR(255),`phoneno` INT
) TYPE = InnoDB;

CREATE VIEW `OVO_Car`
AS ( SELECT PK_Car,TO_Car.color AS color,TO_Car.model AS model
FROM TO_Car
 );

CREATE VIEW `OVO_Person`
AS ( SELECT PK_Person,TO_Person.age AS age,TO_Person.name AS name,TO_Person.phoneno AS phoneno
FROM TO_Person
 );

ALTER TABLE `ASO_ownedCars_owner` ADD CONSTRAINT `CONASO_ownedCars_ownerFK_ownedCars`
FOREIGN KEY (`FK_ownedCars`) REFERENCES `TO_Car`(`PK_Car`);

ALTER TABLE `ASO_ownedCars_owner` ADD CONSTRAINT `CONASO_ownedCars_ownerFK_owner`
FOREIGN KEY (`FK_owner`) REFERENCES `TO_Person`(`PK_Person`);

-- Context: Car
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() = 1
CREATE OR REPLACE VIEW OcarOcl01 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() = 1
CREATE OR REPLACE VIEW OcarOcl02 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() = 1
CREATE OR REPLACE VIEW OcarOcl03 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.color = 'black')),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() = 1
CREATE OR REPLACE VIEW OcarOcl04 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OVO_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() =1
CREATE OR REPLACE VIEW OcarOcl05 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OVO_Car AS temp7
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car))),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl06: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))
CREATE OR REPLACE VIEW OcarOcl06 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.PK_Car
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE NOT(temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OVO_Car AS temp7
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car)))))));

-- Context: Car
-- Expression: inv carOcl07: Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size() = 1
CREATE OR REPLACE VIEW OcarOcl07 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OVO_Car AS temp7
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car))) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl08: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size() = 1
CREATE OR REPLACE VIEW OcarOcl08 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OVO_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl09: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum() = 1
CREATE OR REPLACE VIEW OcarOcl09 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (((SELECT IFNULL(SUM(IF(temp1.color IS NULL,NULL,1)),0)
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OVO_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl10: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))
CREATE OR REPLACE VIEW OcarOcl10 AS
(SELECT * FROM OVO_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.PK_Car
FROM OVO_Car AS temp1
INNER JOIN ASO_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OVO_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OVO_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OVO_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE NOT('black' NOT IN
  ((SELECT temp7.color
FROM OVO_Car AS temp7
INNER JOIN ASO_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OVO_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OVO_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASO_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OVO_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car)))))));