CREATE TABLE `kostmatch_private` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) NOT NULL, 
  `kostensoort_id` bigint(20) NOT NULL,
  `match_text` varchar(200) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

CREATE TABLE `btwmatch_private` (
  `id` bigint(20) NOT NULL auto_increment,
  `kostmatch_id` bigint(20) NOT NULL,
  `btw_type` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

grant delete on kostmatch_private;
grant delete on btwmatch_private;


CREATE TABLE `splitmatch_private` (
  `id` bigint(20) NOT NULL auto_increment,
  `kostmatch_id` bigint(20) NOT NULL,
  `percentage` int(2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;



