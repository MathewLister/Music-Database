-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: leia.cs.spu.edu    Database: bettagj_db
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'I Guess I Just Feel Like','Pop',2019,8,14),(2,'Continuum','Pop',2006,1,14),(3,'The Eminem Show','Rap',2002,7,6),(4,'Purple Rain','Rock',1984,2,15),(5,'All That You Can\'t Leave Behind','Rock',2000,3,17),(6,'It\'s About Time','Pop',2006,4,3),(7,'Elephunk','Pop',2003,5,16),(8,'True','Electronic',2013,6,21);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (1,'Ed Sheeran'),(2,'The Lonely Island'),(3,'Jonas Brothers'),(4,'Ke$ha'),(5,'Katy Perry'),(6,'Eminem'),(7,'Michael Jackson'),(8,'P!nk'),(9,'Shakira'),(10,'Cher'),(11,'Slash'),(12,'Usher'),(13,'Tupac'),(14,'John Mayer'),(15,'Prince'),(16,'Black Eyed Peas'),(17,'U2'),(18,'Snoop Dogg'),(19,'Bob Marley'),(20,'Adam Levine'),(21,'Avicii'),(22,'Obie Trice'),(23,'Dina Rae'),(24,'Dr. Dre'),(25,'Nate Dogg'),(26,'Halie Jade');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `artist_song`
--

LOCK TABLES `artist_song` WRITE;
/*!40000 ALTER TABLE `artist_song` DISABLE KEYS */;
INSERT INTO `artist_song` VALUES (1,22,10),(2,23,14),(3,24,18),(4,25,19),(5,26,20),(26,6,2),(27,6,3),(28,6,4),(29,6,5),(30,6,6),(31,6,7),(32,6,8),(33,6,9),(34,6,10),(35,6,11),(36,6,12),(37,6,13),(38,6,14),(39,6,15),(40,6,16),(41,6,17),(42,6,18),(43,6,19),(44,6,20),(45,6,21),(46,14,22),(52,14,23),(53,14,24),(54,14,25),(55,14,26),(56,14,27),(57,14,28),(58,14,29),(59,14,30),(60,14,31),(61,14,32),(62,14,34),(63,14,33),(64,15,35),(65,15,36),(66,15,37),(67,15,38),(68,15,39),(69,15,40),(70,15,41),(71,15,42),(72,15,43),(73,17,44),(74,17,45),(75,17,46),(76,17,47),(77,17,48),(78,17,49),(79,17,50),(80,17,51),(81,17,52),(82,17,53),(83,17,54),(84,3,57),(85,3,58),(86,3,59),(87,3,60),(88,3,61),(89,3,62),(90,3,63),(91,3,64),(92,3,65),(93,3,66),(94,3,67),(95,16,68),(96,16,69),(97,16,70),(98,16,71),(99,16,72),(100,16,73),(101,16,74),(102,16,75),(103,16,76),(104,16,77),(105,16,78),(106,16,79),(107,16,80),(108,16,81),(109,16,82),(110,21,83),(111,21,84),(112,21,85),(113,21,86),(114,21,87),(115,21,88),(116,21,89),(117,21,90),(118,21,91),(119,21,92),(120,21,93),(121,21,94);
/*!40000 ALTER TABLE `artist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `concert`
--

LOCK TABLES `concert` WRITE;
/*!40000 ALTER TABLE `concert` DISABLE KEYS */;
INSERT INTO `concert` VALUES (1,'2016-12-31 18:00:00','Sydney New Year','Sydney, Australia',2),(2,'2015-06-01 18:00:00','Mawazine Festival 2015','OLM Souissi',3);
/*!40000 ALTER TABLE `concert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'Aware Records LLC'),(2,'NPG Records, Inc.'),(3,'Universal-Island Records Ltd.'),(4,'Jonas Enterprises'),(5,'Interscope Records'),(6,'Avicii Music AB'),(7,'Aftermath Entertainment'),(8,'Columbia Records');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Bob','Marley'),(2,'Andy','Samberg'),(3,'Jorma','Staccome'),(4,'Akiva','Schaffer'),(5,'Ed','Sheeran'),(6,'Adam','Levine'),(7,'Nick','Jonas'),(8,'Joe','Jonas'),(9,'Kevin','Jonas'),(10,'John ','Mayer'),(11,'Prince','Nelson'),(12,'Paul','Hewson'),(13,'David','Evans'),(14,'Adam','Clayton'),(15,'Larry','Mullen'),(16,'Ivan','McCormick'),(17,'Peter','Martin'),(18,'Dick','Evans'),(19,'Stacy','Ferguson'),(20,'William','Adams'),(21,'Jaime','Gomez'),(22,'Allan','Lindo'),(23,'Marshall','Mathers'),(24,'Tim','Bergling'),(25,'Obie','Trice'),(26,'Dina ','Rae'),(27,'Andre','Romelle'),(28,'Nathaniel','Hale'),(29,'Hailie','Jade');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `person_artist`
--

LOCK TABLES `person_artist` WRITE;
/*!40000 ALTER TABLE `person_artist` DISABLE KEYS */;
INSERT INTO `person_artist` VALUES (1,1,5),(2,2,2),(3,2,3),(4,2,4),(5,3,7),(6,3,8),(7,3,9),(8,14,10),(9,15,11),(10,16,19),(11,17,12),(12,17,13),(13,17,14),(14,17,15),(15,17,16),(16,17,17),(17,17,18),(18,16,20),(19,16,21),(20,16,22),(21,19,1),(22,6,23),(23,20,6),(24,21,24),(25,22,25),(26,23,26),(27,24,27),(28,25,28),(29,26,29);
/*!40000 ALTER TABLE `person_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Pop Jams','2019-09-20 21:42:49'),(2,'Sydney New Year','2019-12-02 07:27:56'),(3,'Mawazine Festival 2015','2019-12-02 07:43:14'),(4,'fake1','2019-12-04 07:19:47'),(5,'fake2','2019-12-04 07:19:57'),(6,'Test1','2019-12-05 07:09:20'),(8,'ggg','2019-12-05 07:18:17'),(9,'rock','2019-12-05 07:31:57');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `playlist_song`
--

LOCK TABLES `playlist_song` WRITE;
/*!40000 ALTER TABLE `playlist_song` DISABLE KEYS */;
INSERT INTO `playlist_song` VALUES (1,2,1),(2,3,1),(3,4,1),(4,5,1),(5,6,1),(6,7,1),(7,11,1),(8,13,1),(9,14,1),(10,71,1),(11,72,1),(12,73,1),(13,74,1),(14,75,1),(15,75,1),(16,19,2),(17,72,2),(18,74,2),(19,64,2),(20,65,2),(21,33,2),(22,34,2),(23,42,2),(24,83,3),(25,84,3),(26,85,3),(27,86,3),(28,87,3),(29,88,3),(30,89,3),(31,90,3),(32,91,3),(33,92,3),(34,93,3),(35,94,3),(36,33,1),(37,34,1),(38,2,4),(39,14,5);
/*!40000 ALTER TABLE `playlist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (2,'Curtains Up - Skit',30,3),(3,'White America',325,3),(4,'Business',252,3),(5,'Cleanin\' Out My Closet',298,3),(6,'Square Dance',324,3),(7,'The Kiss - Skit',76,3),(8,'Soldier',226,3),(9,'Say Goodbye Hollywood',273,3),(10,'Drips',286,3),(11,'Without Me',290,3),(12,'Paul Rosenberg - Skit',23,3),(13,'Sing For The Moment',340,3),(14,'Superman',350,3),(15,'Hailie\'s Song',321,3),(16,'Steve Berman - Skit',33,3),(17,'When The Music Stops',269,3),(18,'Say What You Say',310,3),(19,'\'Till I Collapse',298,3),(20,'My Dad\'s Gone Crazy',267,3),(21,'Curtains Close',62,3),(22,'I Guess I Just Feel Like',285,1),(23,'Waiting On The World To Change',201,2),(24,'I Don\'t Trust Myself (With Loving You)',498,2),(25,'Belief',242,2),(26,'Gravity',246,2),(27,'The Heart Of Life',198,2),(28,'Vultures',251,2),(29,'Stop This Train',285,2),(30,'Slow Dancing in a Burning Room',242,2),(31,'Bold as Love',258,2),(32,'Dreaming with a Broken Heart',246,2),(33,'In Repair',368,2),(34,'I\'m Gonna Find Another You',163,2),(35,'Let\'s Go Crazy',280,4),(36,'Take Me with U',234,4),(37,'The Beautiful Ones',314,4),(38,'Computer Blue',240,4),(39,'Darling Nikki',254,4),(40,'When Doves Cry',343,4),(41,'I Would Die 4 U',179,4),(42,'Baby I\'m a Star',264,4),(43,'Purple Rain',521,4),(44,'Beautiful Day',246,5),(45,'Stuck In A Moment You Can\'t Get Out Of',272,5),(46,'Elevation',226,5),(47,'Walk On',295,5),(48,'Kite',263,5),(49,'In A Little While',217,5),(50,'Wild Honey',225,5),(51,'Peace On Earth',286,5),(52,'When I Look At The World',255,5),(53,'New York',328,5),(54,'Grace',331,5),(57,'What I Go to School For',214,6),(58,'Time for Me to Fly',186,6),(59,'Year 3000',206,6),(60,'One Day At a Time',235,6),(61,'6 Minutes',186,6),(62,'Mandy',168,6),(63,'You Just Don\'t Know It',217,6),(64,'I Am What I Am',130,6),(65,'Underdog',195,6),(66,'7:05',227,6),(67,'Please Be Mine',194,6),(68,'Hands Up',216,7),(69,'Labor Day (It\'s A Holiday)',239,7),(70,'Let\'s Get Retarded',216,7),(71,'Hey Mama',215,7),(72,'Shut Up',296,7),(73,'Smells Like Funk',305,7),(74,'Latin Girls',378,7),(75,'Sexy',284,7),(76,'Fly Away',215,7),(77,'The Boogie That Be',312,7),(78,'The Apl Song',175,7),(79,'Anxiety',218,7),(80,'Where Is The Love?',273,7),(81,'Let\'s Get It Started - Spike Mix',217,7),(82,'Third Eye',220,7),(83,'Wake Me Up',247,8),(84,'You Make Me',233,8),(85,'Hey Brother',255,8),(86,'Addicted To You',148,8),(87,'Dear Boy',479,8),(88,'Liar Liar',239,8),(89,'Shame On Me',251,8),(90,'Lay Me Down',300,8),(91,'Hope There\'s Someone',381,8),(92,'Heart Upon My Sleeve',283,8),(93,'Canyons',441,8),(94,'All You Need Is Love',381,8);
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-07  0:18:07
