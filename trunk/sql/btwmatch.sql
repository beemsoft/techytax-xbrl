CREATE TABLE `project`.`btwmatch` (
  `id` BIGINT  NOT NULL AUTO_INCREMENT,
  `kostmatch_id` BIGINT  NOT NULL,
  `btw_type` CHAR(1)  NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = MyISAM;

ALTER TABLE `project`.`btwmatch` MODIFY COLUMN `btw_type` CHAR(1)  CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL;