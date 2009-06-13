CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(100) NOT NULL default '',  
  `datum` date NOT NULL default '2006-07-01',
  `saldo` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;