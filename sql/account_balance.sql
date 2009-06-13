CREATE TABLE `account_balance` (
  `id` bigint(20) NOT NULL auto_increment,
  `account_id` bigint(20) NOT NULL default '0',  
  `datum` date NOT NULL default '2006-07-01',
  `balance` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;