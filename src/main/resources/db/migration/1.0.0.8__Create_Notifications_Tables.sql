-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.8__Create_Notificatins_Tables.sql
-- Author: Josh Van Dyke
-- Date: 2020-08-27
-- Issue: ACMAPP-165: Create Table to store information related to website user 
-- notifications.
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-165: START
-- ---------------------------------------------------------------------------------

CREATE TABLE `development`.`notification_types` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `type_id_UNIQUE` (`type_id` ASC));

CREATE TABLE `development`.`notifications` (
  `notifications_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_id` INT UNSIGNED NOT NULL DEFAULT 1,
  `notification_message` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`notifications_id`),
  UNIQUE INDEX `notifications_id_UNIQUE` (`notifications_id` ASC));
  

-- ---------------------------------------------------------------------------------
-- ACMAPP-165: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION
