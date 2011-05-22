USE techytax;

-- MySQL dump 10.11
--
-- Host: localhost    Database: techytax
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
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `kostensoort`
--

LOCK TABLES `kostensoort` WRITE;
/*!40000 ALTER TABLE `kostensoort` DISABLE KEYS */;
INSERT INTO `kostensoort` VALUES 
(0,'costtype.none',0,0,0,0,0),
(1,'costtype.income.current.account',1,1,1,0,0)
,(2,'costtype.expense.current.account',0,1,1,1,0),
(3,'costtype.expense.current.account.erroneous',0,0,1,0,0),
(4,'costtype.deposit',1,0,1,0,0),
(5,'costtype.withdrawal',0,0,1,0,0),
(6,'costtype.expense.other.account.ignore',0,0,0,0,0),
(7,'costtype.expense.other.account.erroneous',0,1,0,1,0),
(8,'costtype.expense.travel.other.account.erroneous',0,0,0,1,0),
(9,'costtype.expense.travel',0,1,1,1,0),
(11,'costtype.creditcard.partial.payment',0,0,1,0,0),
(10,'costtype.creditcard.partial.payment.erroneous',0,0,0,0,0),
(12,'costtype.vat.return',0,0,1,0,0),
(13,'costtype.business.food',0,0,1,1,0),
(14,'costtype.business.car',0,1,1,1,0),
(15,'costtype.pension.reservation',0,0,0,0,0),
(16,'costtype.investment.depreciate',0,1,1,0,1),
(18,'costtype.debt.interest',0,0,1,0,0),
(19,'costtype.business.car.other.account',0,1,0,1,0),
(20,'costtype.business.food.other.account',0,0,0,1,0),
(21,'costtype.business.travel.credit.card',0,1,0,0,0),
(22,'costtype.business.literature.credit.card.no.vat',0,0,0,1,0),
(23,'costtype.business.car.depreciation',0,0,0,1,0),
(24,'costtype.business.car.fiscal.income',0,0,0,1,0),
(25,'costtype.depreciation',0,0,0,1,0),
(26,'costtype.investment.depreciate.other.account',0,1,0,0,1),
(27,'costtype.advertorial',0,1,0,1,0),
(28,'costtype.advertorial.no.vat',0,0,0,1,0),
(29,'costtype.income.tax',0,0,1,0,0),
(30,'costtype.income.tax.return',1,0,1,0,0),
(31,'costtype.road.tax',0,0,1,1,0),
(32,'costtype.expense.creditcard',0,1,0,1,0),
(33,'costtype.interest',0,0,0,0,0),
(34,'costtype.vat.return.incoming.other.account',1,0,0,0,0),
(35,'costtype.vat.correction.depreciation',0,0,0,1,0),
(36,'costtype.vat.correction.private',0,1,0,0,0),
(37,'costtype.business.car.non.deductible',0,0,1,0,0);

/*!40000 ALTER TABLE `kostensoort` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-06-14 12:56:47
