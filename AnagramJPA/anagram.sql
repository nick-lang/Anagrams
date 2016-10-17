-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema anagramdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `anagramdb` ;

-- -----------------------------------------------------
-- Schema anagramdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `anagramdb` DEFAULT CHARACTER SET utf8 ;
USE `anagramdb` ;

-- -----------------------------------------------------
-- Table `words`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `words` ;

CREATE TABLE IF NOT EXISTS `words` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `word` VARCHAR(45) NULL DEFAULT NULL,
  `hash` DOUBLE NULL DEFAULT NULL,
  `count` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO admin;
 DROP USER admin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
