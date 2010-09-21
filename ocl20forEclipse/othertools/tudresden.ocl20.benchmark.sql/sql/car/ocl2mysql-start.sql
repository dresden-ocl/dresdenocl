--
-- Table structure for table `Car`
--

CREATE TABLE `Car` (
  `pk` mediumint(9) NOT NULL auto_increment,
  `model` varchar(200) default NULL,
  `color` varchar(200) default NULL,
  PRIMARY KEY  (`pk`)
) ENGINE=MyISAM;

CREATE TABLE `ownership` (
  `owner` mediumint(9) NOT NULL,
  `ownedCars` mediumint(9) NOT NULL,
  KEY `ownedCar` (`ownedCars`),
  KEY `owner` (`owner`)
) ENGINE=MyISAM;


CREATE TABLE `Person` (
  `pk` mediumint(9) NOT NULL auto_increment,
  `name` varchar(200) default NULL,
  `age` int(11) default NULL,
  `phoneno` int(11) default NULL,
  PRIMARY KEY  (`pk`)
) ENGINE=MyISAM;

delimiter //
create procedure forAll0()
begin
declare done Int default 0 ;
declare var Int;
declare crs cursor for select ownership.owner AS value
from (select pk as value from Car) as temp0
left join ownership on temp0.value = ownership.ownedCars
where ownership.owner is not null;
declare continue handler for sqlstate '02000' set done = 1;
drop table if exists forAll0;
create table forAll0(value int);
insert into forAll0(value) values (0); open crs;
repeat
fetch crs into var;
if not done then
update forAll0 set value = 0 where(
select count(*) > 0 AS value from (
select ownership.ownedCars as value
from (select var as value) as temp1
left join ownership on temp1.value = ownership.owner
where ownership.ownedCars is not null) as temp2) = 0;
if exists (select 1 from forAll0 where value = 0)
then set done = 1;
end if;
end if;
until done end repeat;
close crs;
end; //

CREATE PROCEDURE collect00()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 VARCHAR(200);
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value VARCHAR(200));
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
      SELECT Car.color AS value 
      FROM (SELECT var00 AS value) AS temp2 
      LEFT JOIN Car 
      ON temp2.value = Car.pk;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE collect01()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
      SELECT (SELECT Car.color AS value 
         FROM (SELECT var00 AS value) AS temp2 
         LEFT JOIN Car 
         ON temp2.value = Car.pk) 
      <> (SELECT 'black' AS value) as value;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE collect02()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
       SELECT ownership.ownedCars AS value 
       FROM (SELECT ownership.owner AS value 
          FROM (SELECT var00 AS value) AS temp3 
          LEFT JOIN ownership 
          ON temp3.value = ownership.ownedCars 
          WHERE ownership.owner IS NOT NULL) AS temp2 
       LEFT JOIN ownership 
       ON temp2.value = ownership.owner 
       WHERE ownership.ownedCars IS NOT NULL;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE collect03()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
       SELECT (SELECT var00 AS value) 
       IN (SELECT ownership.ownedCars AS value 
          FROM (SELECT ownership.owner AS value 
             FROM (SELECT var00 AS value) AS temp3 
             LEFT JOIN ownership 
             ON temp3.value = ownership.ownedCars 
             WHERE ownership.owner IS NOT NULL) AS temp2 
          LEFT JOIN ownership 
          ON temp2.value = ownership.owner 
       WHERE ownership.ownedCars IS NOT NULL) as value;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE select00()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE body Boolean DEFAULT false ;
DECLARE var00 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS select00;
CREATE TABLE select00(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    select value into body 
       from (SELECT (SELECT var00 AS value) 
          IN (SELECT ownership.ownedCars AS value 
             FROM (SELECT ownership.owner AS value 
                FROM (SELECT var00 AS value) AS temp3 
                LEFT JOIN ownership 
                ON temp3.value = ownership.ownedCars 
                WHERE ownership.owner IS NOT NULL) AS temp2 
             LEFT JOIN ownership 
             ON temp2.value = ownership.owner 
             WHERE ownership.ownedCars IS NOT NULL) as value) as temp;
    if body then 
      INSERT INTO select00(value) values (var00);
    end if;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE collect04()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 VARCHAR(200);
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value VARCHAR(200));
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
      SELECT Car.color AS value 
      FROM (SELECT ownership.ownedCars AS value 
         FROM (SELECT ownership.owner AS value 
            FROM (SELECT var00 AS value) AS temp4 
            LEFT JOIN ownership 
            ON temp4.value = ownership.ownedCars 
            WHERE ownership.owner IS NOT NULL) AS temp3 
         LEFT JOIN ownership 
         ON temp3.value = ownership.owner 
         WHERE ownership.ownedCars IS NOT NULL) AS temp2 
      LEFT JOIN Car 
      ON temp2.value = Car.pk;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE collect05()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE var00 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS collect00;
CREATE TABLE collect00(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var00;
  IF NOT done THEN
    INSERT INTO collect00(value) 
      SELECT COUNT(*) AS value 
      FROM (SELECT Car.color AS value 
         FROM (SELECT ownership.ownedCars AS value 
            FROM (SELECT ownership.owner AS value 
               FROM (SELECT var00 AS value) AS temp4 
               LEFT JOIN ownership 
               ON temp4.value = ownership.ownedCars 
               WHERE ownership.owner IS NOT NULL) AS temp3 
            LEFT JOIN ownership 
            ON temp3.value = ownership.owner 
            WHERE ownership.ownedCars IS NOT NULL) AS temp2 
         LEFT JOIN Car 
         ON temp2.value = Car.pk) AS temp5;
  END IF;
UNTIL done END REPEAT;
CLOSE crs;
END; //

CREATE PROCEDURE forAll1()
BEGIN
DECLARE done INT DEFAULT 0 ;
DECLARE result boolean DEFAULT true;
DECLARE var0 INT;
DECLARE crs CURSOR FOR 
      SELECT ownership.ownedCars AS value 
      FROM (SELECT ownership.owner AS value 
         FROM (SELECT pk AS value FROM Car) AS temp1 
         LEFT JOIN ownership 
         ON temp1.value = ownership.ownedCars 
         WHERE ownership.owner IS NOT NULL) AS temp0 
      LEFT JOIN ownership 
      ON temp0.value = ownership.owner 
      WHERE ownership.ownedCars IS NOT NULL;
DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
DROP TABLE IF EXISTS forAll0;
CREATE TABLE forAll0(value INT);
OPEN crs;
REPEAT
  FETCH crs INTO var0;
  IF NOT done THEN
  select value into result from 
      (SELECT (SELECT 'black' AS value) 
      NOT IN (SELECT Car.color AS value 
         FROM (SELECT ownership.ownedCars AS value 
            FROM (SELECT ownership.owner AS value 
               FROM (SELECT var0 AS value) AS temp4 
               LEFT JOIN ownership 
               ON temp4.value = ownership.ownedCars 
               WHERE ownership.owner IS NOT NULL) AS temp3 
            LEFT JOIN ownership 
            ON temp3.value = ownership.owner 
            WHERE ownership.ownedCars IS NOT NULL) AS temp2 
         LEFT JOIN Car ON temp2.value = Car.pk) as value) as temp;
  IF NOT result THEN
    SET done = 1;
  END IF;
  END IF;
UNTIL done END REPEAT;
INSERT INTO forAll0(value) values (result);
CLOSE crs;
END; //

delimiter ;


