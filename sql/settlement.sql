CREATE TABLE `settlement` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `startDate` date NOT NULL default '2006-07-01',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;