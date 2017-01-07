CREATE DATABASE  IF NOT EXISTS `lan_airlines` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lan_airlines`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: lan_airlines
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `airbus`
--

DROP TABLE IF EXISTS `airbus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airbus` (
  `id_airbus` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_airbus` int(11) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `id_ruta` bigint(20) NOT NULL,
  PRIMARY KEY (`id_airbus`),
  KEY `id_horario` (`id_ruta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airbus`
--

LOCK TABLES `airbus` WRITE;
/*!40000 ALTER TABLE `airbus` DISABLE KEYS */;
INSERT INTO `airbus` VALUES (1,123,'LAN-123',100,2),(3,123,'LAN-123',100,4),(4,123,'LAN-123',100,6),(5,433,'LAN-433',100,5),(6,433,'LAN-433',100,7);
/*!40000 ALTER TABLE `airbus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asientos`
--

DROP TABLE IF EXISTS `asientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asientos` (
  `id_asientos` bigint(20) NOT NULL AUTO_INCREMENT,
  `n_asiento` int(11) NOT NULL,
  `id_airbus` bigint(20) NOT NULL,
  PRIMARY KEY (`id_asientos`),
  KEY `id_airbus` (`id_airbus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asientos`
--

LOCK TABLES `asientos` WRITE;
/*!40000 ALTER TABLE `asientos` DISABLE KEYS */;
/*!40000 ALTER TABLE `asientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto` (
  `id_boleto` bigint(20) NOT NULL,
  `id_clase` int(11) NOT NULL,
  `ida` varchar(5) NOT NULL,
  `vuelta` varchar(5) NOT NULL,
  `id_vuelo` bigint(20) NOT NULL,
  PRIMARY KEY (`id_boleto`),
  KEY `id_clase` (`id_clase`),
  KEY `id_vuelo` (`id_vuelo`),
  CONSTRAINT `boleto_ibfk_1` FOREIGN KEY (`id_clase`) REFERENCES `clase` (`id_clase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clase`
--

DROP TABLE IF EXISTS `clase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clase` (
  `id_clase` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id_clase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clase`
--

LOCK TABLES `clase` WRITE;
/*!40000 ALTER TABLE `clase` DISABLE KEYS */;
/*!40000 ALTER TABLE `clase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_reservaciones`
--

DROP TABLE IF EXISTS `detalle_reservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_reservaciones` (
  `id_detalle_reservaciones` bigint(20) NOT NULL,
  `id_reservaciones` int(11) NOT NULL,
  `id_boleto` int(11) NOT NULL,
  PRIMARY KEY (`id_detalle_reservaciones`),
  KEY `id_reservaciones` (`id_reservaciones`),
  KEY `id_boleto` (`id_boleto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_reservaciones`
--

LOCK TABLES `detalle_reservaciones` WRITE;
/*!40000 ALTER TABLE `detalle_reservaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_reservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ventas`
--

DROP TABLE IF EXISTS `detalle_ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_ventas` (
  `id_detalle_ventas` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ventas` int(11) NOT NULL,
  `id_reservaciones` int(11) NOT NULL,
  `id_detalle_reservaciones` int(11) NOT NULL,
  PRIMARY KEY (`id_detalle_ventas`),
  KEY `id_reservaciones` (`id_reservaciones`),
  KEY `id_detalle_reservaciones` (`id_detalle_reservaciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ventas`
--

LOCK TABLES `detalle_ventas` WRITE;
/*!40000 ALTER TABLE `detalle_ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarios` (
  `id_horarios` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_ruta` bigint(20) NOT NULL,
  `horario` time NOT NULL,
  PRIMARY KEY (`id_horarios`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES (1,1,'22:00:00'),(2,2,'12:00:00'),(4,4,'11:00:00'),(5,5,'05:45:00'),(7,6,'06:00:00'),(8,7,'16:00:00');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` int(11) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  PRIMARY KEY (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservaciones`
--

DROP TABLE IF EXISTS `reservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservaciones` (
  `id_reservaciones` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `id_vuelo` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_reservaciones`),
  KEY `id_persona` (`id_persona`),
  KEY `id_vuelo` (`id_vuelo`),
  CONSTRAINT `reservaciones_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservaciones`
--

LOCK TABLES `reservaciones` WRITE;
/*!40000 ALTER TABLE `reservaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ruta`
--

DROP TABLE IF EXISTS `ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruta` (
  `id_ruta` bigint(20) NOT NULL AUTO_INCREMENT,
  `ruta` varchar(40) NOT NULL,
  `partida` varchar(20) NOT NULL,
  `destino` varchar(20) NOT NULL,
  `duracion` double NOT NULL,
  `codigo` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_ruta`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruta`
--

LOCK TABLES `ruta` WRITE;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` VALUES (1,'GYE - CUE','GUAYAQUIL','CUENCA',0.3,NULL),(2,'GYE - LOJ','GUAYAQUIL','LOJA',45,NULL),(4,'UIO - MAC','QUITO','MACHALA',60,NULL),(5,'UIO - LOJ','QUITO','LOJA',90,NULL),(6,'LOJ - GYE','LOJA','GUAYAQUIL',45,NULL),(7,'LOJ - UIO','LOJA','QUITO',55,NULL);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(15) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `fechas` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ADMINISTRADOR','1234567890','DAVID EUGENIO','CAMPO FABARA','0987654321','CATAMAYO','fotodebate@gmail.com','h','campofabara','1234','2016-12-20'),(3,'USUARIO_EXTERNO','0915101620','JOSE LUIS','TAPIA LOPEZ ','0981709441','ANDRES MARIN 407 Y SUCRE','joseluistapia09@gmail.com','30/Diciembre/2016','JTAPIA2','JTAPIA20915101620','2016-12-30'),(4,'USUARIO_EXTERNO','1101925186','EUGENIO MARIN','CAMPOVERDE AGUILAR','022498245','QUITO - CONDADO ','eucampo@hotmail.es','1/Enero/2017','eucampo','1234','2017-01-01');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `id_ventas` bigint(20) NOT NULL,
  `id_reservaciones` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `Total` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_ventas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelo`
--

DROP TABLE IF EXISTS `vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vuelo` (
  `id_vuelo` bigint(20) NOT NULL,
  `id_airbus` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id_vuelo`),
  KEY `id_airbus` (`id_airbus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelo`
--

LOCK TABLES `vuelo` WRITE;
/*!40000 ALTER TABLE `vuelo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-06 11:02:51
