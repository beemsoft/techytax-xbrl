CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(100) NOT NULL default '',
  `number` varchar(20) NOT NULL default '',  
  `name` varchar(100) NOT NULL default '',
  `date_opened` date NOT NULL,
  `date_closed` date,  
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

ALTER TABLE accounts ADD COLUMN `type` int(1) default '0' NOT NULL AFTER date_closed;