CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(100) NOT NULL default '',
  `number` bigint(20) NOT NULL default '0',  
  `name` varchar(100) NOT NULL default '',
  `date_opened` date NOT NULL default '2006-07-01',
  `date_closed` date,  
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;