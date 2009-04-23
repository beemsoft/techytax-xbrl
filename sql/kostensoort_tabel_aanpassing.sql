ALTER TABLE `project`.`kostensoort` ADD COLUMN `bijschrijving` TINYINT(1)  NOT NULL DEFAULT 0,
 ADD COLUMN `btwVerrekenbaar` TINYINT(1)  NOT NULL DEFAULT 0 AFTER `bijschrijving`,
 ADD COLUMN `balansMeerekenen` TINYINT(1)  NOT NULL DEFAULT 0 AFTER `btwVerrekenbaar`;
 
ALTER TABLE `project`.`kostensoort` ADD COLUMN `investering` TINYINT(1)  DEFAULT 0 AFTER `aftrekbaar`;
 
