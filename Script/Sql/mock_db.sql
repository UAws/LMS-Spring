-- MySQL dump 10.13  Distrib 5.6.51, for osx10.16 (x86_64)
--
-- Host: 172.16.0.78    Database: oop
-- ------------------------------------------------------
-- Server version	5.6.49-log

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) NOT NULL,
  `password` char(255) NOT NULL DEFAULT '-1',
  `title` char(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `user_level` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `People_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (2,'tutor01','2','Lecturer',1,3),(3,'teacher01','-1','Lecturer',1,3),(5,'tutor02','-1','Student',1,1),(6,'zzc','-1','Student',1,1),(7,'TestUser','-1','Student',0,1),(8,'Jack1','$2a$10$jh9dUevpr7FgTkxHLuNrme97U8Qmc.jV.Gh8z2tjs1284rrnoLj92','Lecturer',1,3),(223,'admin','$2a$10$j9ItYr7BOUWqYfJPhH2UXuP9DqpRgMkNzfD8VG4BYsVD1u6OCRHAq','Student',1,1),(224,'TestUserPassword','$2a$10$36BSHNNJdN4kAlgKK/S/ZuevopWFnoSodyiOl5RwzbPpxJoi/tM9u','Tutor',1,2);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_subject`
--

DROP TABLE IF EXISTS `people_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people_subject` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `subject_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`subject_id`),
  KEY `peopleSubject_subject_subject_id_fk` (`subject_id`),
  CONSTRAINT `peopleSubject_People_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `people` (`user_id`),
  CONSTRAINT `peopleSubject_subject_subject_id_fk` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_subject`
--

LOCK TABLES `people_subject` WRITE;
/*!40000 ALTER TABLE `people_subject` DISABLE KEYS */;
INSERT INTO `people_subject` VALUES (2,1),(3,1),(8,1),(224,1),(2,2),(3,2),(8,2),(224,2),(2,75),(3,75),(8,75),(224,75),(2,76),(3,76),(8,76),(224,76),(2,78),(3,78),(8,78),(224,78),(2,84),(3,84),(8,84),(224,84),(2,91),(3,91),(8,91),(224,91);
/*!40000 ALTER TABLE `people_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_subject_id_uindex` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'subject6666'),(2,'subject05'),(75,'CS6666c'),(76,'subject06'),(78,'subject0777213asd'),(84,'asd123subject01123'),(91,'123123asd');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-12 23:27:01
