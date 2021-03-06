-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: librarygrails
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `guest` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `year` int(11) NOT NULL,
  `out_date` varchar(255) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1000,2,'Harry Potter and the Sorcerers Stone',NULL,'',1997,NULL,'JK Rowling'),
  (1001,0,'Harry Potter and the Chamber of Secrets',NULL,'',1998,NULL,'JK Rowling'),
  (1002,0,'Harry Potter and the Prisoner of Azkaban',NULL,'',1999,NULL,'JK Rowling'),
  (1003,4,'Harry Potter and the Goblet of Fire',NULL,'',2000,NULL,'JK Rowling'),
  (1004,0,'Harry Potter and the order of the Phoenix',NULL,'',2003,NULL,'JK Rowling'),
  (1005,0,'Harry Potter and the Half-Blood Prince',NULL,'',2005,NULL,'JK Rowling'),
  (1006,0,'Harry Potter and the Deathly Hallows',NULL,'',2007,NULL,'JK Rowling'),
  (1007,0,'A Game of Thrones',NULL,'',1996,NULL,'George RR Martin'),
  (1008,0,'A Clash of Kings',NULL,'',1999,NULL,'George RR Martin'),
  (1009,0,'A Storm of Swords',NULL,'',2000,NULL,'George RR Martin'),
  (1010,0,'A Feast for Crows',NULL,'',2005,NULL,'George RR Martin'),
  (1011,0,'A Dance with Dragons',NULL,'',2011,NULL,'George RR Martin'),
  (1012,0,'The Hunger Games',NULL,'',2008,NULL,'Suzanne Collins'),
  (1013,0,'Catching Fire',NULL,'',2009,NULL,'Suzanne Collins'),
  (1014,0,'Mockingjay',NULL,'',2010,NULL,'Suzanne Collins'),
  (1015,0,'To Kill a Mockingbird',NULL,'',1960,NULL,'Harper Lee'),
  (1016,0,'Pride and Prejudice',NULL,'',1813,NULL,'Jane Austen'),
  (1017,0,'The Book Thief',NULL,'',2005,NULL,'Markus Zusak'),
  (1018,0,'Animal Farm',NULL,'',1945,NULL,'George Orwell'),
  (1019,0,'Gone with the Wind',NULL,'',1936,NULL,'Margaret Mitchell'),
  (1020,0,'The Hitchhikers Guide to the Galaxy',NULL,'',1979,NULL,'Douglas  Adams'),
  (1021,0,'The Giving Tree',NULL,'',1964,NULL,'Shel Silverstein'),
  (1022,0,'The Da Vinci Code',NULL,'',2003,NULL,'Dan Brown'),
  (1023,0,'Les Miserables',NULL,'',1862,NULL,'Victor Hugo'),
  (1024,0,'Lord of the Flies',NULL,'',1954,NULL,'William Golding'),
  (1025,0,'The Alchemist',NULL,'',2988,NULL,'Paulo Coelho'),
  (1026,0,'Crime and Punishment',NULL,'',1866,NULL,'Fyodor Dostoyevsky'),
  (1027,0,'Altered Carob',NULL,'',2002,NULL,'Richard Morgan');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `fee` double NOT NULL,
  `guest_id` varchar(9) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j1kqktryvfq4rkaful8f0xtmc` (`guest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,2,1.5,'R60OT2Rm','Pam','Beesley'),
  (2,0,0,'7IUeUKVb','Jim','Halper'),
  (3,0,0,'BmagVYBM','Dwight','Schrute'),
  (4,0,0,'vS5tUQir','Michael','Scott'),
  (5,0,0,'OUCP7X2X','Kevin','Malone'),
  (6,0,0,'uycsw1Mp','Andy','Bernard'),
  (7,0,0,'X6peEXNS','Toby','Flenderson'),
  (8,0,0,'vsKxZBUF','Ryan','Howard'),
  (9,0,0,'rMc4K4Li','Ron','Swanson'),
  (10,0,0,'j4BWSSmK','Leslie','Knope');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1000);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-05 14:39:43
