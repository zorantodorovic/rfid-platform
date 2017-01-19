-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: iot_platform_logic
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `chip`
--

DROP TABLE IF EXISTS `chip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Chip_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chip`
--

LOCK TABLES `chip` WRITE;
/*!40000 ALTER TABLE `chip` DISABLE KEYS */;
INSERT INTO `chip` VALUES (1,'AdminsChipAtFER','TEST'),(2,'DrugiChip',NULL);
/*!40000 ALTER TABLE `chip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `query`
--

DROP TABLE IF EXISTS `query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `query` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `startDateTime` datetime DEFAULT NULL,
  `endDateTime` datetime DEFAULT NULL,
  `sensorId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Query_id_uindex` (`id`),
  KEY `Query_Sensor_id_fk` (`sensorId`),
  CONSTRAINT `Query_Sensor_id_fk` FOREIGN KEY (`sensorId`) REFERENCES `sensor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `query`
--

LOCK TABLES `query` WRITE;
/*!40000 ALTER TABLE `query` DISABLE KEYS */;
INSERT INTO `query` VALUES (1,'2017-01-08 02:49:47','2017-01-11 02:49:52',1,100),(2,'2017-01-06 04:18:43','2017-01-06 04:18:46',3,54);
/*!40000 ALTER TABLE `query` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` datetime DEFAULT NULL,
  `sensorId` int(11) DEFAULT NULL,
  `chipId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Record_id_uindex` (`id`),
  KEY `Record_Sensor_id_fk` (`sensorId`),
  KEY `Record_Chip_id_fk` (`chipId`),
  CONSTRAINT `Record_Chip_id_fk` FOREIGN KEY (`chipId`) REFERENCES `chip` (`id`),
  CONSTRAINT `Record_Sensor_id_fk` FOREIGN KEY (`sensorId`) REFERENCES `sensor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1,'2016-12-21 13:09:20',1,1),(2,'2016-12-21 13:09:59',2,1),(3,'2015-12-21 13:10:19',1,2),(5,'2016-12-21 13:09:30',2,1),(6,'2016-12-21 13:09:30',2,1),(8,'1993-08-02 18:09:24',3,1),(9,'1970-01-18 05:27:19',1,1),(10,'1970-01-18 05:27:20',1,1),(11,'1970-01-18 05:27:20',1,1),(12,'2017-01-19 16:28:44',1,1),(13,'2017-01-19 16:53:40',1,1),(14,'2017-01-19 16:53:40',1,1),(15,'2017-01-19 16:53:40',1,1),(16,'2017-01-19 16:53:40',1,1),(17,'2017-01-19 16:53:40',1,1),(18,'2017-01-19 16:53:40',1,1),(19,'2017-01-19 16:53:40',1,1),(20,'2017-01-19 16:53:40',1,1),(21,'2017-01-19 16:53:40',1,1),(22,'2017-01-19 16:53:40',1,1),(23,'2017-01-19 16:53:40',1,1),(24,'2017-01-19 16:53:40',1,1),(25,'2017-01-19 16:53:40',1,1),(26,'2017-01-19 16:53:40',1,1),(27,'2017-01-19 16:53:40',1,1),(28,'2017-01-19 16:53:40',1,1),(29,'2017-01-19 16:53:40',1,1),(30,'2017-01-19 16:53:40',1,1),(31,'2017-01-19 16:53:40',1,1),(32,'2017-01-19 16:53:40',1,1),(33,'2017-01-19 16:53:40',1,1),(34,'2017-01-19 16:53:40',1,1),(35,'2017-01-19 16:53:40',1,1),(36,'2017-01-19 16:53:40',1,1),(37,'2017-01-19 16:53:40',1,1),(38,'2017-01-19 16:53:40',1,1),(39,'2017-01-19 16:53:40',1,1),(40,'2017-01-19 16:53:40',1,1),(41,'2017-01-19 16:53:40',1,1),(42,'2017-01-19 16:53:40',1,1),(43,'2017-01-19 16:53:40',1,1),(44,'2017-01-19 16:53:40',1,1),(45,'2017-01-19 16:53:40',1,1),(46,'2017-01-19 16:53:40',1,1);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sensorType` int(11) NOT NULL,
  `ipAddress` varchar(60) NOT NULL,
  `userId` int(11) NOT NULL,
  `sensorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Sensor_id_uindex` (`id`),
  UNIQUE KEY `Sensor_ipAdress_uindex` (`ipAddress`),
  KEY `Sensor_User_id_fk` (`userId`),
  CONSTRAINT `Sensor_User_id_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensor`
--

LOCK TABLES `sensor` WRITE;
/*!40000 ALTER TABLE `sensor` DISABLE KEYS */;
INSERT INTO `sensor` VALUES (1,1,'192.168.1.74',1,'FEROVAC'),(2,2,'192.168.1.75',1,NULL),(3,1,'192.168.1.44',10,NULL),(4,2,'google.hr',11,NULL);
/*!40000 ALTER TABLE `sensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sensorsink`
--

DROP TABLE IF EXISTS `sensorsink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sensorsink` (
  `sensorId` int(11) NOT NULL,
  `sinkId` int(11) NOT NULL,
  PRIMARY KEY (`sensorId`,`sinkId`),
  KEY `SensorSink_Sink_id_fk` (`sinkId`),
  CONSTRAINT `SensorSink_Sensor_id_fk` FOREIGN KEY (`sensorId`) REFERENCES `sensor` (`id`),
  CONSTRAINT `SensorSink_Sink_id_fk` FOREIGN KEY (`sinkId`) REFERENCES `sink` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensorsink`
--

LOCK TABLES `sensorsink` WRITE;
/*!40000 ALTER TABLE `sensorsink` DISABLE KEYS */;
INSERT INTO `sensorsink` VALUES (1,1),(3,1),(1,2),(2,2),(4,2);
/*!40000 ALTER TABLE `sensorsink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sink`
--

DROP TABLE IF EXISTS `sink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(100) CHARACTER SET utf8 NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Sink_id_uindex` (`id`),
  KEY `Sink_User_id_fk` (`userId`),
  CONSTRAINT `Sink_User_id_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sink`
--

LOCK TABLES `sink` WRITE;
/*!40000 ALTER TABLE `sink` DISABLE KEYS */;
INSERT INTO `sink` VALUES (1,'iot_platform_records.Record',1),(2,'iot_platform_logic.Record',1);
/*!40000 ALTER TABLE `sink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_id_uindex` (`id`),
  UNIQUE KEY `User_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin'),(10,'sensor3','sensor3'),(11,'sensor4','sensor4'),(12,'aqq','aqq'),(13,'slon12','slon12');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-19 17:01:54
