-- Context: Car
-- Expression: inv carOcl01: Car.allInstances().owner.ownedCars->size() = 1
CREATE OR REPLACE VIEW carOcl01 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl02: Car.allInstances().owner.ownedCars->collect(x|x.color)->size() = 1
CREATE OR REPLACE VIEW carOcl02 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl03: Car.allInstances().owner.ownedCars->collect(x|x.color <> 'black')->size() = 1
CREATE OR REPLACE VIEW carOcl03 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(NOT (temp1.color = 'black')),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl04: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars)->size() = 1
CREATE OR REPLACE VIEW carOcl04 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OV_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl05: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars->includes(x))->size() =1
CREATE OR REPLACE VIEW carOcl05 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OV_Car AS temp7
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car))),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl06: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars->includes(x))
CREATE OR REPLACE VIEW carOcl06 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.PK_Car
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE NOT(temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OV_Car AS temp7
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car)))))));

-- Context: Car
-- Expression: inv carOcl07: Car.allInstances().owner.ownedCars->select(x|x.owner.ownedCars->includes(x))->size() = 1
CREATE OR REPLACE VIEW carOcl07 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.PK_Car),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE temp1.PK_Car IN
  ((SELECT temp7.PK_Car
FROM OV_Car AS temp7
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car))) = 1)));

-- Context: Car
-- Expression: inv carOcl08: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color)->size() = 1
CREATE OR REPLACE VIEW carOcl08 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(COUNT(temp1.color),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OV_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl09: Car.allInstances().owner.ownedCars->collect(x|x.owner.ownedCars.color->size())->sum() = 1
CREATE OR REPLACE VIEW carOcl09 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (((SELECT IFNULL(SUM(IFNULL(COUNT(temp1.color),0)),0)
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
INNER JOIN OV_Car AS temp7 ON temp6.PK_Car = temp7.PK_Car
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
) = 1)));

-- Context: Car
-- Expression: inv carOcl10: Car.allInstances().owner.ownedCars->forAll(x|x.owner.ownedCars.color->excludes('black'))
CREATE OR REPLACE VIEW carOcl10 AS
(SELECT * FROM OV_Car AS self
WHERE NOT (NOT EXISTS((SELECT temp1.PK_Car
FROM OV_Car AS temp1
INNER JOIN ASS_ownedCars_owner AS temp2 ON temp1.PK_Car = temp2.FK_ownedCars
INNER JOIN OV_Person AS temp3 ON temp2.FK_owner = temp3.PK_Person
INNER JOIN OV_Person AS temp4 ON temp3.PK_Person = temp4.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp5 ON temp4.PK_Person = temp5.FK_owner
INNER JOIN OV_Car AS temp6 ON temp5.FK_ownedCars = temp6.PK_Car
WHERE NOT('black' NOT IN
  ((SELECT temp7.color
FROM OV_Car AS temp7
INNER JOIN ASS_ownedCars_owner AS temp8 ON temp7.PK_Car = temp8.FK_ownedCars
INNER JOIN OV_Person AS temp9 ON temp8.FK_owner = temp9.PK_Person
INNER JOIN OV_Person AS temp10 ON temp9.PK_Person = temp10.PK_Person
INNER JOIN ASS_ownedCars_owner AS temp11 ON temp10.PK_Person = temp11.FK_owner
INNER JOIN OV_Car AS temp12 ON temp11.FK_ownedCars = temp12.PK_Car
WHERE temp12.PK_Car = temp1.PK_Car)))))));