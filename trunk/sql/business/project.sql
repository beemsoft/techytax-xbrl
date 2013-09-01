CREATE TABLE project
(
   id bigint NOT NULL AUTO_INCREMENT,
   user_id bigint(20) NOT NULL,
   code varchar(100) NOT NULL,
   project_description varchar(200) NOT NULL,
   activity_description varchar(200),
   customer_id bigint DEFAULT 0 NOT NULL,
   start_date date DEFAULT '2006-07-01' NOT NULL,
   end_date date DEFAULT '2006-07-01' NOT NULL,
   rate decimal(50,2) DEFAULT 0.00 NOT NULL,
   vatType smallint(1) default '0',
   PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;