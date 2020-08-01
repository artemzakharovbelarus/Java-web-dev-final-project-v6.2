-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Database corporate_education_v2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Database corporate_education_v2
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `corporate_education_v2` DEFAULT CHARACTER SET utf8 ;
USE `corporate_education_v2` ;

-- -----------------------------------------------------
-- Table `corporate_education_v2`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`roles` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `role_title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE INDEX `idRole_UNIQUE` (`idRole` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(90) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `leader_status` TINYINT NOT NULL DEFAULT 0,
  `banned_status` TINYINT NOT NULL DEFAULT 0,
  `online_status` TINYINT NOT NULL DEFAULT 0,
  `user_photo` VARCHAR(45) NULL,
  `idRole` INT NOT NULL DEFAULT 2,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_users_Roles_idx` (`idRole` ASC) VISIBLE,
  CONSTRAINT `fk_users_Roles`
    FOREIGN KEY (`idRole`)
    REFERENCES `corporate_education_v2`.`roles` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`likes`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`likes` (
`idLike` INT NOT NULL AUTO_INCREMENT,
`enabled_status` TINYINT NOT NULL DEFAULT 0,
`idUser` INT NOT NULL,
`idTraining` INT NOT NULL,
PRIMARY KEY (`idLike`),
INDEX `fk_likes_users1_idx` (`idUser` ASC) VISIBLE,
INDEX `fk_likes_trainings1_idx` (`idTraining` ASC) VISIBLE,
CONSTRAINT `fk_likes_users1`
FOREIGN KEY (`idUser`)
REFERENCES `corporate_education_v2`.`users` (`idUser`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `fk_likes_trainings1`
FOREIGN KEY (`idTraining`)
REFERENCES `corporate_education_v2`.`trainings` (`idTraining`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `corporate_education_v2`.`dislikes`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`dislikes` (
`idDislike` INT NOT NULL  AUTO_INCREMENT,
`enabled_status` TINYINT NOT NULL DEFAULT 0,
`idUser` INT NOT NULL,
`idTraining` INT NOT NULL,
PRIMARY KEY (`idDislike`),
INDEX `fk_dislikes_users1_idx` (`idUser` ASC) VISIBLE,
INDEX `fk_dislikes_trainings1_idx` (`idTraining` ASC) VISIBLE,
CONSTRAINT `fk_dislikes_users1`
FOREIGN KEY (`idUser`)
REFERENCES `corporate_education_v2`.`users` (`idUser`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `fk_dislikes_trainings1`
FOREIGN KEY (`idTraining`)
REFERENCES `corporate_education_v2`.`trainings` (`idTraining`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `corporate_education_v2`.`users_additional_info`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`users_additional_info` (
  `idInfo` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `sex` TINYINT NOT NULL DEFAULT 2,
  `birth_date` DATE NULL,
  `github` VARCHAR(90) NULL,
  `linkedIn` VARCHAR(90) NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idInfo`),
  UNIQUE INDEX `idInfo_UNIQUE` (`idInfo` ASC) VISIBLE,
  INDEX `fk_users_additional_info_users1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_users_additional_info_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `corporate_education_v2`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`trainings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`trainings` (
  `idTraining` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(90) NULL,
  `requirements` VARCHAR(180) NULL,
  `information` VARCHAR(180) NULL,
  `deleted_status` TINYINT NOT NULL DEFAULT 0,
  `city` VARCHAR(45) NULL,
  `hours_amount` INT NULL,
  `min_members` INT NULL,
  `max_members` INT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `training_image` MEDIUMBLOB NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idTraining`),
  UNIQUE INDEX `idTraining_UNIQUE` (`idTraining` ASC) VISIBLE,
  INDEX `fk_trainings_users1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_trainings_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `corporate_education_v2`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`teams` (
  `idTeam` INT NOT NULL AUTO_INCREMENT,
  `people_amount` INT NULL,
  `idTraining` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idTeam`),
  UNIQUE INDEX `idTeam_UNIQUE` (`idTeam` ASC) VISIBLE,
  INDEX `fk_teams_trainings1_idx` (`idTraining` ASC) VISIBLE,
  INDEX `fk_teams_users1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_teams_trainings1`
    FOREIGN KEY (`idTraining`)
    REFERENCES `corporate_education_v2`.`trainings` (`idTraining`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teams_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `corporate_education_v2`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`users_have_teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`users_have_teams` (
  `idUser` INT NOT NULL,
  `mark` INT NULL,
  `attendance` FLOAT NULL,
  `feedback` BLOB NULL,
  `idTeam` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_users_has_teams_teams1_idx` (`idTeam` ASC) VISIBLE,
  INDEX `fk_users_has_teams_users1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_teams_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `corporate_education_v2`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_teams_teams1`
    FOREIGN KEY (`idTeam`)
    REFERENCES `corporate_education_v2`.`teams` (`idTeam`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `corporate_education_v2`.`applications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`queries` (
  `idQuery` INT NOT NULL auto_increment,
  `idUser` INT NOT NULL,
  `accepted_status` INT NOT NULL DEFAULT 0,
  `canceled_status` INT DEFAULT 0,
  `idTraining` INT NOT NULL,
  PRIMARY KEY (`idQuery`),
  INDEX `fk_queries_users1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_queries_trainings1`
    FOREIGN KEY (`idTraining`)
    REFERENCES `corporate_education_v2`.`trainings` (`idTraining`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_queries_users1`
    FOREIGN KEY (`idUser`)
    REFERENCES `corporate_education_v2`.`users` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `corporate_education_v2`.`news` (
`idNews` INT NOT NULL AUTO_INCREMENT,
`news_intro` VARCHAR(45) NULL,
`news_text` VARCHAR(45) NULL,
`news_image` VARCHAR(45) NULL,
`idUser` INT NOT NULL,
PRIMARY KEY (`idNews`),
INDEX `fk_News_users1_idx` (`idUser` ASC) VISIBLE,
CONSTRAINT `fk_News_users1`
FOREIGN KEY (`idUser`)
REFERENCES `corporate_education_v2`.`users` (`idUser`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `roles` (`idRole`, `role_title`) VALUES (1, 'Admin');
INSERT INTO `roles` (`idRole`, `role_title`) VALUES (2, 'Student');
INSERT INTO `roles` (`idRole`, `role_title`) VALUES (3, 'Trainer');

INSERT INTO `users` (`username`, `password`, `email`, `user_photo`, `idRole`) VALUES ('ArtemZakharovBY', '$2a$10$0gL6xiomu1yA9.SaP0E4quTg0ZmwAJ0b/z/LH8fohU7geI8cjHRp2', 'temax359x@gmail.com', '/img/artem-photo.jpg', 1);
INSERT INTO `users` (`username`, `password`, `email`, `idRole`) VALUES ('OlgaSmolyakova', '$2a$10$jjljHbnYVwjUrlAYaVBP7ezrYdvR6kVKYzkB8Idud3blXILdqpx0q', 'OlgaSmolyakova@epam.com', 3);
INSERT INTO `users` (`username`, `password`, `email`, `banned_status`, `idRole`) VALUES ('Vania123', '$2a$10$jjljHbnYVwjUrlAYaVBP7ezrYdvR6kVKYzkB8Idud3blXILdqpx0q', 'qwe123@epam.com', true, 2);

INSERT INTO `users_additional_info` (`name`, `surname`, `sex`, `birth_date`, `github`, `linkedIn`, `idUser`) VALUES ('Artem', 'Zakharov', true, '1999-10-16', 'https://github.com/ArtemZakharovBY', 'https://www.linkedin.com/in/artem-zakharov-751b78194/', 1);

INSERT INTO `trainings` (`title`, `requirements`, `information`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('Java web development', 'Владение хорошими знаниями Java EE, JDBC, Maven', 'Данные тренинг обучит технологиям Java EE и таким фреймворкам, как Spring MVC, Hibernate, Angular.js', 'Минск', 20, 15, 20, '2020-07-10', '2020-08-10', 2);
INSERT INTO `trainings` (`title`, `requirements`, `information`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('PHP web development', 'Иметь представление о Laravel', 'Данные тренинг обучит технологиям Laravel, PHPAdmin и Bootstrap 4', 'Минск', 20, 15, 20, '2020-07-10', '2020-08-10', 2);
INSERT INTO `trainings` (`title`, `requirements`, `information`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('Clean code', 'Have basic knowledge in OOP', 'This new training will give you good principles in OOP code writing', 'New-York', 20, 15, 20, '2020-07-10', '2020-08-10', 2);
INSERT INTO `trainings` (`title`, `requirements`, `information`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('Web-services', 'Have some knowledge in REST', 'Данный тренинг обучит вас подходу в разработке веб-сервисов REST', 'Брест', 20, 15, 20, '2020-07-10', '2020-08-10', 2);
INSERT INTO `trainings` (`title`, `requirements`, `information`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('Development methodologies', 'Have some knowledge in Agile', 'This training will give you good knowledge in Development methodologies, after ending the training you will understand Agile, XP methodologies', 'London', 20, 15, 20, '2020-07-10', '2020-08-10', 2);
INSERT INTO `trainings` (`title`, `requirements`, `information`, `deleted_status`, `city`, `hours_amount`, `min_members`, `max_members`, `start_date`, `end_date`, `idUser`) VALUES ('English training', 'Have some knowledge in English (A1)', 'English is necessary nowadays and this training will give a basic knowledge', true, 'Rome', 20, 15, 20, '2020-07-10', '2020-08-10', 2);

INSERT INTO `news` (`news_intro`,`news_text`, `news_image`, `idUser`) VALUES ('Компания Oracle', 'Вышла Java 14, почему же это прорыв?', '/img/oracle-news.jpg', 1);
INSERT INTO `news` (`news_intro`, `news_text`, `news_image`, `idUser`) VALUES ('Training center', 'Наш новый тренинг Development methodologies!', '/img/new-tr.jpg', 1);
