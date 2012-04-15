CREATE TABLE `settlement` (
  `user_id` bigint(20) NOT NULL,
  `description` text NOT NULL,
  `startDate` date NOT NULL default '2006-07-01',
  `purchasePrice` mediumint(3) NOT NULL default '0',
  `startupCosts` decimal(10,2) default '0.00',  
  `nofSquareMetersBusiness` smallint(2) default '0' NOT NULL,
  `nofSquareMetersPrivate` smallint(2) default '0',
  `wozValue` mediumint(3) default '0' NOT NULL,
  `terrainValue` mediumint(3) default '0',
  `nofYearsForDepreciation` smallint(2) not null,
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

