CREATE TABLE external_cost_types (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  code varchar(16) NOT NULL,
  description varchar(200) NOT NULL,  
  level smallint(2) NULL,
  PRIMARY KEY  (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


CREATE TABLE cost_type_export (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cost_type_id bigint(20) NOT NULL,
  external_code varchar(16) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE cost_type_import (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cost_id bigint(20) NOT NULL,
  external_code varchar(16) NOT NULL,  
  PRIMARY KEY  (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


INSERT INTO cost_type_export VALUES 
(1,1,'WOmzNopOlh'),
(2,2,'B'),
(3,4,'WBbeBub'),
(4,5,'WBbeBul'),
(5,7,'B'),
(6,8,'BMvaTev'),
(7,9,'BMvaTev'),
(8,11,'BLimKruCra'),
(9,12,'BSchBtwAtd'),
(10,13,'WPerWkfOcw'),
(11,14,'WBedAut'),
(12,16,'BMvaMeiVvpIna'),
(13,17,'BEivKapProPok'),
(14,18,'BEivKapPrsPsk'),
(15,19,'WBedAut'),
(16,20,'WPerWkfOcw'),
(17,24,'WPerWkbPga'),
(18,26,'BMvaMeiVvpIna'),	
(19,27,'WBedVkkRea'),
(20,28,'WBedVkkRea'),
(21,29,'BLasOlsBep'),
(22,30,'BLasOlsBep'),
(23,31,'WBedAutMot'),
(24,32,'BLimKruCra'),
(25,33,'WFbeRlmObr'),
(26,36,'WBedAutBop'),
(27,39,'WOmzNopOlh'),
(28,40,'WKvdInh'),
(29,41,'BMvaBei'),
(30,42,'WBedHui'),
(31,44,'BMvaBei'),
(32,45,'BMvaBei'),
(33,47,'WBedVkkRea');

