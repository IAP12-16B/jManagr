SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `jManagr` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `jManagr` ;

-- -----------------------------------------------------
-- Table `jManagr`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`Department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(70) NULL,
  `lastname` VARCHAR(70) NULL,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `role` INT(4) NOT NULL,
  `Department` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_User_Department1_idx` (`Department` ASC),
  CONSTRAINT `fk_User_Department1`
    FOREIGN KEY (`Department`)
    REFERENCES `jManagr`.`Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr`.`Resource`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`Resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `icon` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`Ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` INT(5) NOT NULL DEFAULT 0,
  `date` DATETIME NULL,
  `name` VARCHAR(70) NULL,
  `description` TEXT NULL,
  `Department` INT NULL,
  `Agent` INT NULL,
  `Resource` INT NULL,
  `User` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Ticket_Department_idx` (`Department` ASC),
  INDEX `fk_Ticket_User1_idx` (`Agent` ASC),
  INDEX `fk_Ticket_Resource1_idx` (`Resource` ASC),
  INDEX `fk_Ticket_User2_idx` (`User` ASC),
  CONSTRAINT `fk_Ticket_Department`
    FOREIGN KEY (`Department`)
    REFERENCES `jManagr`.`Department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User1`
    FOREIGN KEY (`Agent`)
    REFERENCES `jManagr`.`User` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr`.`Resource` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ticket_User2`
    FOREIGN KEY (`User`)
    REFERENCES `jManagr`.`User` (`id`)
    ON DELETE NO ACTION -- TODO what if user with tickets gets deleted? -> soft-delete
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr`.`Resource_Data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`Resource_Data` (
  `Resource` INT NOT NULL,
  `key` VARCHAR(80) NOT NULL,
  `value` VARCHAR(600) NULL,
  INDEX `fk_Resource_Data_Resource1_idx` (`Resource` ASC),
  PRIMARY KEY (`Resource`, `key`),
  CONSTRAINT `fk_Resource_Data_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jManagr`.`Resource_Children`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jManagr`.`Resource_Children` (
  `Resource` INT NOT NULL,
  `child` INT NOT NULL,
  `order` INT NULL,
  PRIMARY KEY (`Resource`, `child`),
  INDEX `fk_Resource_has_Resource_Resource2_idx` (`child` ASC),
  INDEX `fk_Resource_has_Resource_Resource1_idx` (`Resource` ASC),
  CONSTRAINT `fk_Resource_has_Resource_Resource1`
    FOREIGN KEY (`Resource`)
    REFERENCES `jManagr`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Resource_has_Resource_Resource2`
    FOREIGN KEY (`child`)
    REFERENCES `jManagr`.`Resource` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
