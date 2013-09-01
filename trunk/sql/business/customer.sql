CREATE TABLE customer
(
   id bigint NOT NULL AUTO_INCREMENT,
   user_id bigint(20) NOT NULL,
   name varchar(200),
   description varchar(200),
   address varchar(200),
   contact varchar(200),
   email_invoice varchar(200),
   PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;