CREATE TABLE `private_accounts` (
  `id` bigint(20) NOT NULL auto_increment,
  `year` int(11) NOT NULL,
  `saldo` decimal(10,2) NOT NULL default '0.00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;