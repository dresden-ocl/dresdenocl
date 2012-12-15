-- Generated from OCL2SQL generator(Dresden OCL2)
-- Generation time: 2010-09-21-12-43
-- Template: MySQL(SQL)
-- Modus: typed


CREATE TABLE `ASS_ownedCars_owner` (
`FK_ownedCars` VARCHAR(255) REFERENCES T_Car(PK_Car) ,`FK_owner` VARCHAR(255) REFERENCES T_Person(PK_Person) 
) TYPE = InnoDB;

CREATE TABLE `T_Car` (
`PK_Car` VARCHAR(255) PRIMARY KEY,`color` VARCHAR(255),`model` VARCHAR(255)
) TYPE = InnoDB;

CREATE TABLE `T_Person` (
`PK_Person` VARCHAR(255) PRIMARY KEY,`age` INT,`name` VARCHAR(255),`phoneno` INT
) TYPE = InnoDB;

CREATE VIEW `OV_Car`
AS ( SELECT PK_Car,T_Car.color AS color,T_Car.model AS model
FROM T_Car
 );

CREATE VIEW `OV_Person`
AS ( SELECT PK_Person,T_Person.age AS age,T_Person.name AS name,T_Person.phoneno AS phoneno
FROM T_Person
 );

ALTER TABLE `ASS_ownedCars_owner` ADD CONSTRAINT `CONASS_ownedCars_ownerFK_ownedCars`
FOREIGN KEY (`FK_ownedCars`) REFERENCES `T_Car`(`PK_Car`);

ALTER TABLE `ASS_ownedCars_owner` ADD CONSTRAINT `CONASS_ownedCars_ownerFK_owner`
FOREIGN KEY (`FK_owner`) REFERENCES `T_Person`(`PK_Person`);

-- Context: Car
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() = 1
CREATE OR REPLACE VIEW carOcl01 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() = 1
CREATE OR REPLACE VIEW carOcl02 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() = 1
CREATE OR REPLACE VIEW carOcl03 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.color = 'black')),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() = 1
CREATE OR REPLACE VIEW carOcl04 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_ownedCars = temp3.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() =1
CREATE OR REPLACE VIEW carOcl05 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars IN
  ((SELECT temp3.FK_ownedCars
FROM ASS_ownedCars_owner AS temp3
 INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
 WHERE temp4.FK_ownedCars = temp1.FK_ownedCars))),0)
FROM ASS_ownedCars_owner AS temp1
 INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl06: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))
CREATE OR REPLACE VIEW carOcl06 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_ownedCars
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
WHERE NOT(temp1.FK_ownedCars IN
  ((SELECT temp3.FK_ownedCars
FROM ASS_ownedCars_owner AS temp3
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
 WHERE temp4.FK_ownedCars = temp1.FK_ownedCars)))))));

-- Context: Car
-- Expression: inv carOcl07: Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size() = 1
CREATE OR REPLACE VIEW carOcl07 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
WHERE temp1.FK_ownedCars IN
  ((SELECT temp3.FK_ownedCars
FROM ASS_ownedCars_owner AS temp3
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
 WHERE temp4.FK_ownedCars = temp1.FK_ownedCars))) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl08: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size() = 1
CREATE OR REPLACE VIEW carOcl08 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_ownedCars = temp4.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.FK_owner = temp5.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl09: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum() = 1
CREATE OR REPLACE VIEW carOcl09 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(SUM(IF(temp1.color IS NULL,NULL,1)),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_ownedCars = temp4.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.FK_owner = temp5.FK_owner
) = VALUESTMT)));

-- Context: Car
-- Expression: inv carOcl10: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))
CREATE OR REPLACE VIEW carOcl10 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.FK_ownedCars
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
WHERE NOT('black' NOT IN
  ((SELECT temp3.color
FROM OV_Car AS temp3
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.PK_Car = temp4.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.FK_owner = temp5.FK_owner
 WHERE temp5.FK_ownedCars = temp1.FK_ownedCars)))))));