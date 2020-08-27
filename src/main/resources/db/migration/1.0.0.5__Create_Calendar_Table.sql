-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.4__Create_Calendar_Table.sql
-- Author: Sam Butler
-- Date: 2020-08-24
-- Issue: ACMAPP-98: Create Calendar Table
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-98: START
-- ---------------------------------------------------------------------------------

--
-- TABLE: calendar
--
CREATE TABLE calendar (
  calendar_id     INT          UNSIGNED NOT NULL AUTO_INCREMENT,
  event_name      VARCHAR(150) NOT NULL,
  event_date      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  insert_user_id      INT UNSIGNED NOT NULL,
  insert_date_utc DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (calendar_id)
)ENGINE=InnoDB;

--
-- INDEX: calendar__users_IDX1
--
CREATE INDEX calendar__users_IDX1 ON calendar(insert_user_id);

--
-- FOREIGN KEY: calendar__users_FK1
--
ALTER TABLE calendar ADD CONSTRAINT calendar__users_FK1
    FOREIGN KEY (insert_user_id)
    REFERENCES users (user_id);

-- ---------------------------------------------------------------------------------
-- ACMAPP-98: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION