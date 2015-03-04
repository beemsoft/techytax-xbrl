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

ALTER TABLE customer ADD COLUMN commerceNr numeric(65) AFTER description;
ALTER TABLE customer ADD COLUMN telephone varchar(60) AFTER email_invoice;
ALTER TABLE customer ADD COLUMN fax varchar(60) AFTER telephone;
ALTER TABLE customer ADD COLUMN website varchar(200) AFTER fax;
ALTER TABLE customer ADD COLUMN number numeric(30) AFTER address;
ALTER TABLE customer ADD COLUMN number_extension varchar(60) AFTER number;
ALTER TABLE customer ADD COLUMN postal_code varchar(60) AFTER number_extension;
ALTER TABLE customer ADD COLUMN city varchar(100) AFTER postal_code;

ALTER TABLE customer MODIFY COLUMN number decimal(60);


ALTER TABLE customer drop column commerceNr;
ALTER TABLE customer drop COLUMN telephone;
ALTER TABLE customer drop COLUMN fax;
ALTER TABLE customer drop COLUMN website;
ALTER TABLE customer drop COLUMN number;
ALTER TABLE customer drop COLUMN number_extension;
ALTER TABLE customer drop COLUMN postal_code;
ALTER TABLE customer drop COLUMN city;