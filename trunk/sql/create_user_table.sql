CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `role` VARCHAR(20) NOT NULL,  
  `password` varchar(50) NOT NULL,
  `fullName` varchar(100) NOT NULL,
  `companyName` varchar(100),  
  `latestOnlineTime` datetime NULL default '2006-07-01 00:00:00',
  `blocked` tinyint(1) default '0',  
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO `user` VALUES (0,'guest','guest','guest','Guest',null);

ALTER TABLE `techytax`.`user` ADD COLUMN `phoneNumber` VARCHAR(20) AFTER `fullName`;
ALTER TABLE `techytax`.`user` ADD COLUMN `fiscalNumber` VARCHAR(20) AFTER `phoneNumber`;


ALTER TABLE user ADD COLUMN vatPeriodType smallint(1) default '1' AFTER fiscalNumber;

ALTER TABLE user ADD COLUMN initials varchar(10) not null AFTER fullName;
ALTER TABLE user ADD COLUMN prefix varchar(10) null AFTER initials;
ALTER TABLE user ADD COLUMN surname varchar(100) not null AFTER prefix;

ALTER TABLE user drop column fullName;



