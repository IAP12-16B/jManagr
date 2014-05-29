SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `jManagr` ;
CREATE SCHEMA IF NOT EXISTS `jManagr` DEFAULT CHARACTER SET utf8 ;
USE `jManagr` ;

-- -----------------------------------------------------
-- Table `Department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Department` ;

CREATE TABLE IF NOT EXISTS `Department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL DEFAULT '',
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

CREATE TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(70) NULL DEFAULT NULL,
  `lastname` VARCHAR(70) NULL DEFAULT NULL,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(260) NOT NULL,
  `role` INT(4) NOT NULL DEFAULT 0,
  `Department` INT NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_User_Department1`
    FOREIGN KEY (`Department`)
    REFERENCES `Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `User` (`username` ASC);

CREATE INDEX `fk_User_Department1_idx` ON `User` (`Department` ASC);


-- -----------------------------------------------------
-- Table `Resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Resource` ;

CREATE TABLE IF NOT EXISTS `Resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL DEFAULT '',
  `icon` VARCHAR(255) NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Ticket` ;

CREATE TABLE IF NOT EXISTS `Ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` INT(5) NOT NULL DEFAULT 0,
  `date` DATETIME NULL DEFAULT NULL,
  `name` VARCHAR(70) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `Department` INT NULL DEFAULT NULL,
  `Agent` INT NULL DEFAULT NULL,
  `Resource` INT NULL DEFAULT NULL,
  `User` INT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Ticket_Department`
    FOREIGN KEY (`Department`)
    REFERENCES `Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User1`
    FOREIGN KEY (`Agent`)
    REFERENCES `User` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `Resource` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User2`
    FOREIGN KEY (`User`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_Ticket_Department_idx` ON `Ticket` (`Department` ASC);

CREATE INDEX `fk_Ticket_User1_idx` ON `Ticket` (`Agent` ASC);

CREATE INDEX `fk_Ticket_Resource1_idx` ON `Ticket` (`Resource` ASC);

CREATE INDEX `fk_Ticket_User2_idx` ON `Ticket` (`User` ASC);


-- -----------------------------------------------------
-- Table `ResourceData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResourceData` ;

CREATE TABLE IF NOT EXISTS `ResourceData` (
  `Resource` INT NOT NULL,
  `key` VARCHAR(80) NOT NULL,
  `value` VARCHAR(600) NULL DEFAULT NULL,
  PRIMARY KEY (`Resource`, `key`),
  CONSTRAINT `fk_Resource_Data_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_Resource_Data_Resource1_idx` ON `ResourceData` (`Resource` ASC);


-- -----------------------------------------------------
-- Table `Resource_Children`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Resource_Children` ;

CREATE TABLE IF NOT EXISTS `Resource_Children` (
  `Resource` INT NOT NULL,
  `child` INT NOT NULL,
  `order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Resource`, `child`),
  CONSTRAINT `fk_Resource_has_Resource_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Resource_has_Resource_Resource2`
    FOREIGN KEY (`child`)
    REFERENCES `Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_Resource_has_Resource_Resource2_idx` ON `Resource_Children` (`child` ASC);

CREATE INDEX `fk_Resource_has_Resource_Resource1_idx` ON `Resource_Children` (`Resource` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
