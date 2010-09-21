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
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() > 0
CREATE OR REPLACE VIEW carOcl01 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) > (0))));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() > 0
CREATE OR REPLACE VIEW carOcl02 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() > 0
CREATE OR REPLACE VIEW carOcl03 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() > 0
CREATE OR REPLACE VIEW carOcl04 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() > 0
CREATE OR REPLACE VIEW carOcl05 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl06: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))
CREATE OR REPLACE VIEW carOcl06 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Car FROM OV_Car
   WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
   WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
   WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE NOT (PK_Car IN
  (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))))))
  )
)));

-- Context: Car
-- Expression: inv carOcl07: Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size() > 0
CREATE OR REPLACE VIEW carOcl07 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN ((SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE (PK_Car IN
  (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))))))
  )
)) ) > (0))));

-- Context: Car
-- Expression: inv carOcl08: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size() > 0
CREATE OR REPLACE VIEW carOcl08 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl09: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum() > 0
CREATE OR REPLACE VIEW carOcl09 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(SUM(color) IS NULL,0,SUM(color)) AS 'SUM(color)'
  FROM OV_Car
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))) )) > (0))));

-- Context: Car
-- Expression: inv carOcl10: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))
CREATE OR REPLACE VIEW carOcl10 AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Car FROM OV_Car
   WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
   WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
   WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE NOT ('black' NOT IN
  (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))))) ))
  )
)));

-- Context: Car
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() > 0
CREATE OR REPLACE VIEW carOcl01_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars = SELF.PK_Car))) ) > (0))));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() > 0
CREATE OR REPLACE VIEW carOcl02_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars = SELF.PK_Car))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() > 0
CREATE OR REPLACE VIEW carOcl03_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() > 0
CREATE OR REPLACE VIEW carOcl04_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car))) ) ) > (0))));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() > 0
CREATE OR REPLACE VIEW carOcl05_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))) ) ) > (0)));

-- Context: Car
-- Expression: inv carOcl06: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))
CREATE OR REPLACE VIEW carOcl06_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Car FROM OV_Car
   WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
   WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
   WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car))))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE NOT (PK_Car IN
  (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))))
  )
)));

-- Context: Car
-- Expression: inv carOcl07: Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size() > 0
CREATE OR REPLACE VIEW carOcl07_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(PK_Car) IS NULL,0,COUNT(PK_Car)) AS 'COUNT(PK_Car)'
  FROM (OV_Car)
  WHERE PK_Car IN ((SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE (PK_Car IN
  (SELECT PK_Car FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))))
  )
)) ) > (0)));

-- Context: Car
-- Expression: inv carOcl08: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size() > 0
CREATE OR REPLACE VIEW carOcl08_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(COUNT(color) IS NULL,0,COUNT(color)) AS 'COUNT(color)'
  FROM (OV_Car)
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car))))) ) > (0))));

-- Context: Car
-- Expression: inv carOcl09: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum() > 0
CREATE OR REPLACE VIEW carOcl09_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (((SELECT
  IF(SUM(color) IS NULL,0,SUM(color)) AS 'SUM(color)'
  FROM OV_Car
  WHERE color IN (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car)))) )) > (0))));

-- Context: Car
-- Expression: inv carOcl10: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))
CREATE OR REPLACE VIEW carOcl10_optimize AS
(SELECT * FROM OV_Car AS SELF
WHERE NOT (NOT EXISTS (
  SELECT PK_Car FROM OV_Car
   WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
   WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
   WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
   WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = SELF.PK_Car))))
  AND PK_Car IN (
    SELECT PK_Car FROM OV_Car AS ALIAS4
    WHERE NOT ('black' NOT IN
  (SELECT color FROM OV_Car
 WHERE PK_Car IN (SELECT FK_ownedCars FROM ASS_ownedCars_owner
 WHERE FK_owner IN (SELECT PK_Person FROM OV_Person
 WHERE PK_Person IN (SELECT FK_owner FROM ASS_ownedCars_owner
 WHERE FK_ownedCars IN (SELECT PK_Car FROM OV_Car WHERE PK_Car = ALIAS4.PK_Car)))) ))
  )
)));