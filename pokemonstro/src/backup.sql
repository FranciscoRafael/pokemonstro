-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.27-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-05-06 20:48:36
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
  `image` varchar(250) DEFAULT NULL,
  `idBuilding` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_building_building` (`idBuilding`),
  CONSTRAINT `FK_building_building` FOREIGN KEY (`idBuilding`) REFERENCES `building` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.building: ~0 rows (approximately)
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
/*!40000 ALTER TABLE `building` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.city
CREATE TABLE IF NOT EXISTS `city` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.city: ~0 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
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

-- Dumping data for table pokemonstro.inventory: ~1 rows (approximately)
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` (`ID`) VALUES
	(2);
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
  CONSTRAINT `FK_INVENTORY_ITEM_ITEM_ID` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ID`),
  CONSTRAINT `FK_INVENTORY_ITEM_INVENTORY_ID` FOREIGN KEY (`INVENTORY_ID`) REFERENCES `inventory` (`ID`)
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

-- Dumping data for table pokemonstro.item: ~0 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
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
  `ID` int(11) NOT NULL,
  `IMAGE` varchar(250) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `POSITION` varchar(20) DEFAULT NULL,
  `INVENTORY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLAYER_INVENTORY_ID` (`INVENTORY_ID`),
  CONSTRAINT `FK_PLAYER_INVENTORY_ID` FOREIGN KEY (`INVENTORY_ID`) REFERENCES `inventory` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.player: ~1 rows (approximately)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` (`ID`, `IMAGE`, `NAME`, `POSITION`, `INVENTORY_ID`) VALUES
	(1, NULL, 'teste3', '2 (1,2)', 2);
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


-- Dumping structure for table pokemonstro.savedgame
CREATE TABLE IF NOT EXISTS `savedgame` (
  `ID` int(11) NOT NULL,
  `PLAYER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SAVEDGAME_PLAYER_ID` (`PLAYER_ID`),
  CONSTRAINT `FK_SAVEDGAME_PLAYER_ID` FOREIGN KEY (`PLAYER_ID`) REFERENCES `player` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.savedgame: ~0 rows (approximately)
/*!40000 ALTER TABLE `savedgame` DISABLE KEYS */;
/*!40000 ALTER TABLE `savedgame` ENABLE KEYS */;


-- Dumping structure for table pokemonstro.sequence
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table pokemonstro.sequence: ~1 rows (approximately)
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
	('SEQ_GEN', 50);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
