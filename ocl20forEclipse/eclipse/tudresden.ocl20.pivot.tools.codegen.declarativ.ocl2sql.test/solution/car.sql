-- Context: Car
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() = 1
CREATE OR REPLACE VIEW carOcl01 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
) = 1)));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() = 1
CREATE OR REPLACE VIEW carOcl02 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
) = 1)));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() = 1
CREATE OR REPLACE VIEW carOcl03 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.color = 'black')),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_owner = temp3.FK_owner
) = 1)));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() = 1
CREATE OR REPLACE VIEW carOcl04 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.FK_ownedCars),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_ownedCars = temp3.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
) = 1)));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() =1
CREATE OR REPLACE VIEW carOcl05 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp3.FK_ownedCars IN
  (temp1.FK_ownedCars)),0)
FROM ASS_ownedCars_owner AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.FK_owner = temp2.FK_owner
INNER JOIN ASS_ownedCars_owner AS temp3 ON temp2.FK_ownedCars = temp3.FK_ownedCars
INNER JOIN ASS_ownedCars_owner AS temp4 ON temp3.FK_owner = temp4.FK_owner
) = 1)));

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
WHERE temp4.FK_ownedCars = temp1.FK_ownedCars))) = 1)));

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
) = 1)));

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
) = 1)));

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