SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `jManagr_test` ;
CREATE SCHEMA IF NOT EXISTS `jManagr_test` DEFAULT CHARACTER SET utf8 ;
USE `jManagr_test` ;

-- -----------------------------------------------------
-- Table `jManagr_test`.`Department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`Department` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`Department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr_test`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`User` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(70) NULL DEFAULT NULL,
  `lastname` VARCHAR(70) NULL DEFAULT NULL,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `role` INT(4) NOT NULL,
  `Department` INT NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_User_Department1_idx` (`Department` ASC),
  CONSTRAINT `fk_User_Department1`
    FOREIGN KEY (`Department`)
    REFERENCES `jManagr_test`.`Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr_test`.`Resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`Resource` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`Resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `icon` VARCHAR(255) NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `deleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr_test`.`Ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`Ticket` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`Ticket` (
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
  INDEX `fk_Ticket_Department_idx` (`Department` ASC),
  INDEX `fk_Ticket_User1_idx` (`Agent` ASC),
  INDEX `fk_Ticket_Resource1_idx` (`Resource` ASC),
  INDEX `fk_Ticket_User2_idx` (`User` ASC),
  CONSTRAINT `fk_Ticket_Department`
    FOREIGN KEY (`Department`)
    REFERENCES `jManagr_test`.`Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User1`
    FOREIGN KEY (`Agent`)
    REFERENCES `jManagr_test`.`User` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr_test`.`Resource` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User2`
    FOREIGN KEY (`User`)
    REFERENCES `jManagr_test`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr_test`.`ResourceData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`ResourceData` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`ResourceData` (
  `Resource` INT NOT NULL,
  `key` VARCHAR(80) NOT NULL,
  `value` VARCHAR(600) NULL DEFAULT NULL,
  INDEX `fk_Resource_Data_Resource1_idx` (`Resource` ASC),
  PRIMARY KEY (`Resource`, `key`),
  CONSTRAINT `fk_Resource_Data_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr_test`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr_test`.`Resource_Children`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `jManagr_test`.`Resource_Children` ;

CREATE TABLE IF NOT EXISTS `jManagr_test`.`Resource_Children` (
  `Resource` INT NOT NULL,
  `child` INT NOT NULL,
  `order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Resource`, `child`),
  INDEX `fk_Resource_has_Resource_Resource2_idx` (`child` ASC),
  INDEX `fk_Resource_has_Resource_Resource1_idx` (`Resource` ASC),
  CONSTRAINT `fk_Resource_has_Resource_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr_test`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Resource_has_Resource_Resource2`
    FOREIGN KEY (`child`)
    REFERENCES `jManagr_test`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
