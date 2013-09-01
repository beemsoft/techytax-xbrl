CREATE TABLE calendar_event
(
   id bigint NOT NULL AUTO_INCREMENT,
   user_id bigint(20) NOT NULL,
   beginDate datetime NOT NULL default '2006-07-01 00:00:00',
   endDate datetime NOT NULL default '2006-07-01 00:00:00',
   headerColor varchar(7),
   contentColor varchar(7),
   title varchar(100),   
   content varchar(200),
   project_id bigint(20),
   unitsOfWork tinyint(2) default '0',
   isBillable tinyint(1) NOT NULL default '0',
   travelingByCarCostDeclaration decimal(50,2) NOT NULL default '0.00',
   otherCostDeclaration decimal(50,2) NOT NULL default '0.00',   
   vatTypeForOtherCostDeclaration CHAR(1)  NOT NULL,
   PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

ALTER TABLE techytax.calendar_event MODIFY COLUMN unitsOfWork FLOAT;