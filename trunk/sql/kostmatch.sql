CREATE TABLE `project`.`kostmatch` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `kostensoort_id` BIGINT  NOT NULL,
  `match` VARCHAR(100)  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;

ALTER TABLE `project`.`kostmatch` CHANGE COLUMN `match` `match_text` VARCHAR(100)  CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
