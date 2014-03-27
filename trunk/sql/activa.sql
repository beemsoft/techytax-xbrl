CREATE TABLE `techytax`.`activa` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `activasoort_id` BIGINT  NOT NULL DEFAULT 0,
  `kost_id` BIGINT  NOT NULL,
  `boekjaar` INT  NOT NULL,
  `saldo_begin` BIGINT  NOT NULL,
  `saldo_eind` BIGINT  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;

ALTER TABLE `techytax`.`activa` DROP COLUMN `boekjaar`,
 DROP COLUMN `saldo_begin`,
 DROP COLUMN `saldo_eind`,
 ADD COLUMN `einddatum` DATE  AFTER `kost_id`;
 
 
ALTER TABLE activa ADD COLUMN nofYearsForDepreciation smallint(2) not null AFTER kost_id;
ALTER TABLE activa ADD COLUMN startDate date NULL after nofYearsForDepreciation;
ALTER TABLE activa ADD COLUMN remainingValue decimal(50) NULL;

ALTER TABLE settlement ADD COLUMN id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT FIRST;
ALTER TABLE settlement DROP PRIMARY KEY;

