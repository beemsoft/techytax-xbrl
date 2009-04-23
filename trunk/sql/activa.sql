CREATE TABLE `project`.`activa` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `activasoort_id` BIGINT  NOT NULL DEFAULT 0,
  `kost_id` BIGINT  NOT NULL,
  `boekjaar` INT  NOT NULL,
  `saldo_begin` BIGINT  NOT NULL,
  `saldo_eind` BIGINT  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;

ALTER TABLE `project`.`activa` DROP COLUMN `boekjaar`,
 DROP COLUMN `saldo_begin`,
 DROP COLUMN `saldo_eind`,
 ADD COLUMN `einddatum` DATE  AFTER `kost_id`;