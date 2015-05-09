-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.27-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-05-09 16:58:01
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for pokemonstro
CREATE DATABASE IF NOT EXISTS `pokemonstro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pokemonstro`;


-- Dumping structure for table pokemonstro.attack
CREATE TABLE IF NOT EXISTS `attack` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POWER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.attack: ~0 rows (approximately)
/*!40000 ALTER TABLE `attack` DISABLE KEYS */;
/*!40000 ALTER TABLE `attack` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.building
CREATE TABLE IF NOT EXISTS `building` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `EXTERNALBUILDING_ID` int(11) DEFAULT NULL,
  `CITY_ID` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_building_building` (`EXTERNALBUILDING_ID`),
  KEY `FK_building_city` (`CITY_ID`),
  CONSTRAINT `FK_building_building` FOREIGN KEY (`EXTERNALBUILDING_ID`) REFERENCES `building` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_building_city` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.building: ~3 rows (approximately)
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` (`id`, `Name`, `x`, `y`, `EXTERNALBUILDING_ID`, `CITY_ID`) VALUES
	(1, 'Academia', 20, 50, NULL, 1),
	(2, 'porta', 25, 55, 1, 1),
	(3, 'janela', 30, 50, 1, 1);
/*!40000 ALTER TABLE `building` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.city
CREATE TABLE IF NOT EXISTS `city` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.city: ~1 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`ID`, `NAME`) VALUES
	(1, 'catralandia');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.effect
CREATE TABLE IF NOT EXISTS `effect` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POWER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.effect: ~0 rows (approximately)
/*!40000 ALTER TABLE `effect` DISABLE KEYS */;
/*!40000 ALTER TABLE `effect` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.inventory
CREATE TABLE IF NOT EXISTS `inventory` (
  `ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.inventory: ~0 rows (approximately)
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.inventory_item
CREATE TABLE IF NOT EXISTS `inventory_item` (
  `ID` int(11) NOT NULL,
  `QTY` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `ITEM_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INVENTORY_ITEM_INVENTORY_ID` (`INVENTORY_ID`),
  KEY `FK_INVENTORY_ITEM_ITEM_ID` (`ITEM_ID`),
  CONSTRAINT `FK_INVENTORY_ITEM_INVENTORY_ID` FOREIGN KEY (`INVENTORY_ID`) REFERENCES `inventory` (`ID`),
  CONSTRAINT `FK_INVENTORY_ITEM_ITEM_ID` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.inventory_item: ~0 rows (approximately)
/*!40000 ALTER TABLE `inventory_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_item` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.inventory_pokemonstro
CREATE TABLE IF NOT EXISTS `inventory_pokemonstro` (
  `ID` int(11) NOT NULL,
  `QTY` int(11) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `POKEMONSTRO_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_INVENTORY_POKEMONSTRO_POKEMONSTRO_ID` (`POKEMONSTRO_ID`),
  KEY `FK_INVENTORY_POKEMONSTRO_INVENTORY_ID` (`INVENTORY_ID`),
  CONSTRAINT `FK_INVENTORY_POKEMONSTRO_INVENTORY_ID` FOREIGN KEY (`INVENTORY_ID`) REFERENCES `inventory` (`ID`),
  CONSTRAINT `FK_INVENTORY_POKEMONSTRO_POKEMONSTRO_ID` FOREIGN KEY (`POKEMONSTRO_ID`) REFERENCES `pokemonstro` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.inventory_pokemonstro: ~0 rows (approximately)
/*!40000 ALTER TABLE `inventory_pokemonstro` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_pokemonstro` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.item
CREATE TABLE IF NOT EXISTS `item` (
  `ID` int(11) NOT NULL,
  `IMAGE` varchar(250) DEFAULT NULL,
  `LIFE` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.item: ~2 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`ID`, `IMAGE`, `LIFE`, `NAME`, `TYPE`) VALUES
	(0, NULL, 1, 'whey', 'cura'),
	(1, NULL, 1, 'anabol', 'revive');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.item_effect
CREATE TABLE IF NOT EXISTS `item_effect` (
  `idEffect` int(11) NOT NULL,
  `idItem` int(11) NOT NULL,
  PRIMARY KEY (`idEffect`,`idItem`),
  KEY `FK_Item_Effect_idItem` (`idItem`),
  CONSTRAINT `FK_Item_Effect_idEffect` FOREIGN KEY (`idEffect`) REFERENCES `effect` (`ID`),
  CONSTRAINT `FK_Item_Effect_idItem` FOREIGN KEY (`idItem`) REFERENCES `item` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.item_effect: ~0 rows (approximately)
/*!40000 ALTER TABLE `item_effect` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_effect` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.player
CREATE TABLE IF NOT EXISTS `player` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IMAGE` varchar(250) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POSITION` varchar(20) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  `CITY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLAYER_INVENTORY_ID` (`INVENTORY_ID`),
  KEY `FK_player_city` (`CITY_ID`),
  CONSTRAINT `FK_player_city` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.player: ~3 rows (approximately)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` (`ID`, `IMAGE`, `NAME`, `POSITION`, `INVENTORY_ID`, `CITY_ID`) VALUES
	(1, NULL, 'danilo', '(2,2)', NULL, 1),
	(151, NULL, 'pastel', '(0,0)', NULL, 1),
	(152, NULL, 'danilo', NULL, NULL, 1);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.pokemonstro
CREATE TABLE IF NOT EXISTS `pokemonstro` (
  `ID` int(11) NOT NULL,
  `IMAGE` varchar(250) DEFAULT NULL,
  `LIFE` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.pokemonstro: ~0 rows (approximately)
/*!40000 ALTER TABLE `pokemonstro` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemonstro` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.pokemonstro_attack
CREATE TABLE IF NOT EXISTS `pokemonstro_attack` (
  `idAttack` int(11) NOT NULL,
  `idPokemonstro` int(11) NOT NULL,
  PRIMARY KEY (`idAttack`,`idPokemonstro`),
  KEY `FK_Pokemonstro_Attack_idPokemonstro` (`idPokemonstro`),
  CONSTRAINT `FK_Pokemonstro_Attack_idAttack` FOREIGN KEY (`idAttack`) REFERENCES `attack` (`ID`),
  CONSTRAINT `FK_Pokemonstro_Attack_idPokemonstro` FOREIGN KEY (`idPokemonstro`) REFERENCES `pokemonstro` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.pokemonstro_attack: ~0 rows (approximately)
/*!40000 ALTER TABLE `pokemonstro_attack` DISABLE KEYS */;
/*!40000 ALTER TABLE `pokemonstro_attack` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.sequence
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
	('SEQ_GEN', 300);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
