USE techytax;

-- MySQL dump 10.11
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.0.77

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activa`
--

DROP TABLE IF EXISTS `activa`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `activa` (
  `id` bigint(20) NOT NULL auto_increment,
  `balans_id` bigint(20) NOT NULL default '0',
  `kost_id` bigint(20) NOT NULL,
  `einddatum` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `balans`
--

DROP TABLE IF EXISTS `balans`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `balans` (
  `id` bigint(20) NOT NULL auto_increment,
  `balanssoort_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `balanssoort`
--

DROP TABLE IF EXISTS `balanssoort`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `balanssoort` (
  `id` bigint(20) NOT NULL auto_increment,
  `omschrijving` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `boekwaarde`
--

DROP TABLE IF EXISTS `boekwaarde`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `boekwaarde` (
  `id` bigint(20) NOT NULL auto_increment,
  `balans_id` bigint(20) default NULL,
  `boekjaar` int(11) NOT NULL,
  `saldo` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `btwmatch`
--

DROP TABLE IF EXISTS `btwmatch`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `btwmatch` (
  `id` bigint(20) NOT NULL auto_increment,
  `kostmatch_id` bigint(20) NOT NULL,
  `btw_type` char(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `kosten`
--

DROP TABLE IF EXISTS `kosten`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `kosten` (
  `id` bigint(20) NOT NULL auto_increment,
  `bedrag` decimal(10,2) NOT NULL default '0.00',
  `datum` date NOT NULL default '2006-07-01',
  `omschrijving` varchar(100) NOT NULL default '',
  `btw` decimal(9,2) NOT NULL default '0.00',
  `kostensoort_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=706 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `kostensoort`
--

DROP TABLE IF EXISTS `kostensoort`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `kostensoort` (
  `id` bigint(20) NOT NULL auto_increment,
  `omschrijving` varchar(100) NOT NULL default '',
  `bijschrijving` tinyint(1) NOT NULL default '0',
  `btwVerrekenbaar` tinyint(1) NOT NULL default '0',
  `balansMeetellen` tinyint(1) NOT NULL default '0',
  `aftrekbaar` tinyint(1) NOT NULL default '0',
  `investering` tinyint(1) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `kostmatch`
--

DROP TABLE IF EXISTS `kostmatch`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `kostmatch` (
  `id` bigint(20) NOT NULL auto_increment,
  `kostensoort_id` bigint(20) NOT NULL,
  `match_text` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `restwaarde`
--

DROP TABLE IF EXISTS `restwaarde`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `restwaarde` (
  `id` bigint(20) NOT NULL auto_increment,
  `activa_id` bigint(20) NOT NULL,
  `restwaarde` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-04-22 18:52:35
