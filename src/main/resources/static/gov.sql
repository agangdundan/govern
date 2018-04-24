CREATE DATABASE  IF NOT EXISTS `govern` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `govern`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: govern
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `gov_area`
--

DROP TABLE IF EXISTS `gov_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `countryside` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `village` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `creator_id` varchar(45) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_area`
--

LOCK TABLES `gov_area` WRITE;
/*!40000 ALTER TABLE `gov_area` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_area_notice`
--

DROP TABLE IF EXISTS `gov_area_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_area_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_area_notice`
--

LOCK TABLES `gov_area_notice` WRITE;
/*!40000 ALTER TABLE `gov_area_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_area_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_daily_patrol`
--

DROP TABLE IF EXISTS `gov_daily_patrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_daily_patrol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_person` varchar(45) DEFAULT NULL,
  `report_person_id` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `patrol_type` int(11) DEFAULT '0' COMMENT '1：民间纠纷；2：消防隐患；3：治安问题；4：交通安全；5：建筑安全；6：其他',
  `classify` int(11) DEFAULT '0' COMMENT '1：学生；2：职员；3：教师；4：群众；5：医生；\n6：商人；\n7：其他',
  `place` varchar(45) DEFAULT NULL,
  `addr_gnote` varchar(128) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `pics` varchar(1000) DEFAULT NULL,
  `inspector` varchar(45) DEFAULT NULL,
  `inspect_time` int(11) DEFAULT NULL,
  `result` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0：未处理；1：正在处理；2：已处理',
  `type` int(11) DEFAULT NULL COMMENT '0:日常巡查;1:领导指派;',
  `level` int(11) DEFAULT NULL COMMENT '0普通1严重2紧急\n',
  `complete_time` int(11) DEFAULT NULL,
  `creator_id` varchar(45) DEFAULT NULL,
  `is_enable` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_daily_patrol`
--

LOCK TABLES `gov_daily_patrol` WRITE;
/*!40000 ALTER TABLE `gov_daily_patrol` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_daily_patrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_doc_transmit`
--

DROP TABLE IF EXISTS `gov_doc_transmit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_doc_transmit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `files` varchar(1000) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_doc_transmit`
--

LOCK TABLES `gov_doc_transmit` WRITE;
/*!40000 ALTER TABLE `gov_doc_transmit` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_doc_transmit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_interview`
--

DROP TABLE IF EXISTS `gov_interview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_interview` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relation_id` int(11) DEFAULT NULL,
  `pics` varchar(1000) DEFAULT NULL,
  `view_type` int(11) DEFAULT NULL COMMENT '1：特殊人群',
  `person_status` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `interviewer` varchar(45) DEFAULT NULL,
  `interviewer_id` int(11) DEFAULT NULL,
  `interview_date` int(11) DEFAULT NULL,
  `interview_status` int(11) DEFAULT NULL COMMENT '0：未处理；1：正在处理；2：已处理',
  `create_time` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_interview`
--

LOCK TABLES `gov_interview` WRITE;
/*!40000 ALTER TABLE `gov_interview` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_interview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_news`
--

DROP TABLE IF EXISTS `gov_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `content` text,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_news`
--

LOCK TABLES `gov_news` WRITE;
/*!40000 ALTER TABLE `gov_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gov_report_info`
--

DROP TABLE IF EXISTS `gov_report_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gov_report_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_person` varchar(45) DEFAULT NULL,
  `report_person_id` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `view_type` int(11) DEFAULT '1' COMMENT '1：民间纠纷；2：消防隐患；3：治安问题；4：交通安全；5：建筑安全；6：其他',
  `classify` int(11) DEFAULT '0' COMMENT '1：学生；2：职员；3：教师；4：群众；5：医生；\n6：商人；\n7：其他',
  `addr_type` varchar(255) DEFAULT NULL,
  `addr_gnote` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `pics` varchar(1000) DEFAULT NULL,
  `handler` varchar(45) DEFAULT NULL,
  `handler_id` int(11) DEFAULT NULL,
  `handle_time` int(11) DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `info_status` int(11) DEFAULT '0' COMMENT '0：未处理；1：正在处理；2：已处理\n',
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gov_report_info`
--

LOCK TABLES `gov_report_info` WRITE;
/*!40000 ALTER TABLE `gov_report_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `gov_report_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_file`
--

DROP TABLE IF EXISTS `sys_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_file`
--

LOCK TABLES `sys_file` WRITE;
/*!40000 ALTER TABLE `sys_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `res_url` varchar(255) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,'user:get','/users','GET',NULL,NULL,NULL,NULL,NULL),(2,'user:post','/users','POST',NULL,NULL,NULL,NULL,NULL),(3,'user:put','/users','PUT',NULL,NULL,NULL,NULL,NULL),(4,'user:delete','/users','DELETE',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(45) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'admin',NULL,NULL,NULL,NULL,NULL),(2,'teacher',NULL,NULL,NULL,NULL,NULL),(3,'student',NULL,NULL,NULL,NULL,NULL),(4,'worker',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_Id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_ids` varchar(1000) DEFAULT NULL COMMENT '权限id列表，权限之间用“,”隔开',
  `create_time` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (1,1,'1,2,3,4',NULL,NULL,NULL,NULL),(2,2,'1,2,3',NULL,NULL,NULL,NULL),(3,3,'1,2',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `true_name` varchar(45) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `is_enable` varchar(45) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin',NULL,'admin',NULL,'1899728714',NULL,NULL,NULL,NULL),(2,'phw',NULL,'199798',NULL,'17776304754',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_ids` varchar(1000) DEFAULT NULL COMMENT '用户角色列表，角色之间用“,”隔开',
  `create_time` int(11) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `is_enable` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1,'1,2,3',NULL,NULL,NULL,NULL,NULL),(2,2,'2,3',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-23 19:31:05
