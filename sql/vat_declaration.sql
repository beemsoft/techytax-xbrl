CREATE TABLE vat_declaration (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  declaration_number varchar(40) NULL,  
  payment_feature varchar(16) NULL,
  declaration_feature varchar(40) NULL,  
  timestamp_declared timestamp NULL,
  timestamp_paid timestamp NULL,  
  PRIMARY KEY  (id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

	

