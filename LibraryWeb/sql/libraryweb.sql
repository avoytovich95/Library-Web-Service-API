-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: librarydb
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
-- Table structure for table `bookid`
--

DROP TABLE IF EXISTS `bookid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookid` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookid`
--

LOCK TABLES `bookid` WRITE;
/*!40000 ALTER TABLE `bookid` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `checkedOut` tinyint(1) DEFAULT NULL,
  `outTo` varchar(50) DEFAULT NULL,
  `outAt` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1000,'Harry Potter and the Sorcerers Stone','JK Rowling',1997,0,'',''),
                           (1001,'Harry Potter and the Chamber of Secrets','JK Rowling',1998,0,'',''),
                           (1002,'Harry Potter and the Prisoner of Azkaban','JK Rowling',1999,0,'',''),
                           (1003,'Harry Potter and the Goblet of Fire','JK Rowling',2000,0,'',''),
                           (1004,'Harry Potter and the order of the Phoenix','JK Rowling',2003,0,'',''),
                           (1005,'Harry Potter and the Half-Blood Prince','JK Rowling',2005,0,'',''),
                           (1006,'Harry Potter and the Deathly Hallows','JK Rowling',2007,0,'',''),
                           (1007,'A Game of Thrones','George RR Martin',1996,0,'',''),
                           (1008,'A Clash of Kings','George RR Martin',1999,0,'',''),
                           (1009,'A Storm of Swords','George RR Martin',2000,0,'',''),
                           (1010,'A Feast for Crows','George RR Martin',2005,0,'',''),
                           (1011,'A Dance with Dragons','George RR Martin',2011,0,'',''),
                           (1012,'The Hunger Games','Suzanne Collins',2008,0,'',''),
                           (1013,'Catching Fire','Suzanne Collins',2009,0,'',''),
                           (1014,'Mockingjay','Suzanne Collins',2010,0,'',''),
                           (1015,'To Kill a Mockingbird','Harper Lee',1960,0,'',''),
                           (1016,'Pride and Prejudice','Jane Austen',1813,0,'',''),
                           (1017,'The Book Thief','Markus Zusak',2005,0,'',''),
                           (1018,'Animal Farm','George Orwell',1945,0,'',''),
                           (1019,'Gone with the Wind','Margaret Mitchell',1936,0,'',''),
                           (1020,'The Hitchhikers Guide to the Galaxy','Douglas  Adams',1979,0,'',''),
                           (1021,'The Giving Tree','Shel Silverstein',1964,0,'',''),
                           (1022,'The Da Vinci Code','Dan Brown',2003,0,'',''),
                           (1023,'Les Miserables','Victor Hugo',1862,0,'',''),
                           (1024,'Lord of the Flies','William Golding',1954,0,'',''),
                           (1025,'The Alchemist','Paulo Coelho',1988,0,'',''),
                           (1026,'Crime and Punishment','Fyodor Dostoyevsky',1866,0,'',''),
                           (1027,'Altered Carbon','Richard Morgan',2002,0,'',''),
                           (1028,'Stupid Book','Some Idiot',1995,0,'','');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guests` (
  `id` varchar(50) DEFAULT NULL,
  `first` varchar(50) DEFAULT NULL,
  `last` varchar(50) DEFAULT NULL,
  `fee` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests` VALUES ('Ych0l2Xq','Tom','Jones',1),
                            ('tOnODQyT','Alex','Davis',0),
                            ('OvCDi21L','Felicity','Falster',0),
                            ('kt06kVJ1','Pam','Beesley',0),
                            ('5dYEuU3E','Jim','Halpert',0),
                            ('wtt01Hmy','Dwight','Schrute',0),
                            ('ihFGCdA7','Michael','Scott',0),
                            ('ckC8vT5H','Kevin','Malone',0),
                            ('IlkKcY0m','Andy','Bernard',0),
                            ('1tO8SGqm','Toby','Flenderson',0),
                            ('vjGz8rcz','Ryan','Howard',0);
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-09 14:40:13
