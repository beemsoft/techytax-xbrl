CREATE TABLE `private_expenses` (
  `id` bigint(20) NOT NULL auto_increment,
  `amount` decimal(10,2) NOT NULL default '0.00',
  `is_incoming` tinyint(1) NOT NULL default '0',  
  `datum` date NOT NULL default '2006-07-01',
  `description` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;