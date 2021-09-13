-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: survey_portfolio
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL,
  `category` varchar(26) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Employee Engagement Index'),(2,'Communication'),(3,'Management and Leadership'),(4,' Work Environment'),(5,'Workplace Wellness');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `ques_id` int NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `category_category_id` int DEFAULT NULL,
  PRIMARY KEY (`ques_id`),
  KEY `category_category_id` (`category_category_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,' Do you feel proud to be part of the Company?',1),(2,' How likely are you to recommend our company to your friends?',1),(3,'How likely are you to nominate the company as â€œBest places to workâ€?',1),(4,' Do you look forward to coming to work each morning?',1),(5,' Do you plan to be at this company in the next two years?',1),(6,' Do you feel comfortable contributing ideas and opinions in our workplace?',2),(7,' Do you feel comfortable asking for help if you do not have the skills required to meet your goals?',2),(8,'When you are in any problem relating to work, do you trust your managers to listen?',2),(9,' Do you feel like the management team is transparent?',2),(10,' Do you have a good working relationship like with colleagues?',2),(11,' Do you believe in the approach taken by leaders to take to reach company objectives?',3),(12,' Do you understand the strategic goals of the broader organization?',3),(13,' Does your manager care about you as a person?',3),(14,'Do you feel aligned with the company goals?',3),(15,'Do the people at the executive level contribute to a positive work culture?',3),(16,'Do you feel that the vibe of the workplace is positive and motivating?',4),(17,'How prominent is office politics in the workplace?',4),(18,'Do you have the basic amenities to feel comfortable and relaxed at work?',4),(19,'Does the company provide you with all the tools and materials you need to do your job?',4),(20,'Is your organization dedicated to fostering diversity and inclusion?',4),(21,' Do you think that the company cares about your physical and mental wellbeing?',5),(22,'Do you feel it is important to have a well-defined Corporate wellness program in an organization?',5),(23,' Would you like to have a healthy snacks station in the workplace?',5),(24,'Do you think that the companyâ€™s wellness policies and fitness initiatives are enough?',5),(25,' Would you like to be updated on health-related news and participate in wellness events?',5);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_has_survey`
--

DROP TABLE IF EXISTS `questions_has_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions_has_survey` (
  `questions_ques_id` int DEFAULT NULL,
  `survey_survey_id` int DEFAULT NULL,
  KEY `questions_ques_id` (`questions_ques_id`),
  KEY `survey_survey_id` (`survey_survey_id`),
  CONSTRAINT `questions_has_survey_ibfk_1` FOREIGN KEY (`questions_ques_id`) REFERENCES `questions` (`ques_id`),
  CONSTRAINT `questions_has_survey_ibfk_2` FOREIGN KEY (`survey_survey_id`) REFERENCES `survey` (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_has_survey`
--

LOCK TABLES `questions_has_survey` WRITE;
/*!40000 ALTER TABLE `questions_has_survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `questions_has_survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `response_id` int DEFAULT NULL,
  `questions_ques_id` int DEFAULT NULL,
  `survey_responses_resp_id` int DEFAULT NULL,
  KEY `questions_ques_id` (`questions_ques_id`),
  KEY `survey_responses_resp_id` (`survey_responses_resp_id`),
  CONSTRAINT `response_ibfk_1` FOREIGN KEY (`questions_ques_id`) REFERENCES `questions` (`ques_id`),
  CONSTRAINT `response_ibfk_2` FOREIGN KEY (`survey_responses_resp_id`) REFERENCES `survey` (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int DEFAULT NULL,
  `role_name` varchar(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `survey_id` int NOT NULL,
  `survey_name` varchar(255) DEFAULT NULL,
  `staus` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `close_date` date DEFAULT NULL,
  `category_category_id` int DEFAULT NULL,
  `users_user_id` int DEFAULT NULL,
  PRIMARY KEY (`survey_id`),
  KEY `category_category_id` (`category_category_id`),
  KEY `survey_ibfk_1` (`users_user_id`),
  CONSTRAINT `survey_ibfk_1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `survey_ibfk_2` FOREIGN KEY (`category_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'Employee Engagement Index','pending','2021-01-01','2021-01-01','2021-01-15',1,1),(2,'Employee Engagement Index','pending','2021-02-01','2021-02-01','2021-02-15',1,2),(3,'Communication','pending','2021-03-01','2021-03-01','2021-03-15',2,1);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_responses`
--

DROP TABLE IF EXISTS `survey_responses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey_responses` (
  `resp_id` int NOT NULL,
  `resp_submission_date` date DEFAULT NULL,
  `performance` varchar(16) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  `survey_survey_id` int DEFAULT NULL,
  `users_user_id` int DEFAULT NULL,
  PRIMARY KEY (`resp_id`),
  KEY `users_user_id` (`users_user_id`),
  KEY `survey_survey_id` (`survey_survey_id`),
  CONSTRAINT `survey_responses_ibfk_1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `survey_responses_ibfk_2` FOREIGN KEY (`survey_survey_id`) REFERENCES `survey` (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_responses`
--

LOCK TABLES `survey_responses` WRITE;
/*!40000 ALTER TABLE `survey_responses` DISABLE KEYS */;
INSERT INTO `survey_responses` VALUES (1,'2021-01-01','good','none',1,1),(2,'2021-02-01','excellent ','nice',2,2);
/*!40000 ALTER TABLE `survey_responses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(16) DEFAULT NULL,
  `email` varchar(26) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone_no` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'bunny','bunny@gmail.com','Bunny@123','5555555555','developer','male'),(2,'mayur','mayur@gmail.com','Mayur@123','3333333333','developer','male'),(3,'somu','somu@gmail.com','Somu@123','8888888888','admin','male'),(4,'prachi','prachi','Prachi@123','5555555555','manager','female');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-13  9:32:37
