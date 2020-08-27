-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.5__Create_Tables_For_Classroom_App.acmsql
-- Author: Sam Butler
-- Date: 2020-08-25
-- Issue: ACMAPP-147: Create Tables needed for Classroom to be in place
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-147: START
-- ---------------------------------------------------------------------------------

--
-- TABLE: class
--
CREATE TABLE class (
  class_id        INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name            VARCHAR(100) NOT NULL,
  occupant_count  INT          UNSIGNED NOT NULL,
  room_location   VARCHAR(45)  NOT NULL,
  insert_user_id  INT          UNSIGNED NOT NULL,
  insert_date_utc DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (class_id)
)ENGINE=InnoDB;

--
-- INDEX: class__users_IDX1
--
CREATE INDEX class__users_IDX1 ON class(insert_user_id);

--
-- FOREIGN KEY: class__users_FK1
--
ALTER TABLE class ADD CONSTRAINT class__users_FK1
    FOREIGN KEY (insert_user_id)
    REFERENCES users (user_id);


--
-- TABLE: class_session
--
CREATE TABLE class_session (
  class_id   INT      UNSIGNED NOT NULL,
  start_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  end_time   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB;

--
-- INDEX: class_session__class_IDX1
--
CREATE INDEX class_session__class_IDX1 ON class_session(class_id);

--
-- FOREIGN KEY: class_session__class_FK1
--
ALTER TABLE class_session ADD CONSTRAINT class_session__class_FK1
    FOREIGN KEY (class_id)
    REFERENCES class (class_id);


-- TABLE: class_instructors
--
CREATE TABLE class_instructors (
  class_id   INT UNSIGNED NOT NULL,
  first_name VARCHAR(45)  NOT NULL,
  last_name  VARCHAR(45)  NOT NULL
)ENGINE=InnoDB;

--
-- INDEX: class_instructors__class_IDX1
--
CREATE INDEX class_instructors__class_IDX1 ON class_instructors(class_id);

--
-- FOREIGN KEY: class_instructors__class_FK1
--
ALTER TABLE class_instructors ADD CONSTRAINT class_instructors__class_FK1
    FOREIGN KEY (class_id)
    REFERENCES class (class_id);

-- Create class_events Table
--
CREATE TABLE class_events (
  event_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  event_type        VARCHAR(45) NOT NULL DEFAULT 'NOTE',
  event_name        VARCHAR(45) NOT NULL,
  event_description VARCHAR(200) NOT NULL,
  event_due_date    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  class_id          INT UNSIGNED NOT NULL,
  insert_user_id    INT UNSIGNED NOT NULL,
  insert_date_utc   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (event_id)
)ENGINE=InnoDB;

--
-- INDEX: class_events__class_IDX1
-- INDEX: class_events__users_IDX2
--
CREATE INDEX class_events__class_IDX1 ON class_events(class_id);
CREATE INDEX class_events__users_IDX2 ON class_events(insert_user_id);

--
-- FOREIGN KEY: class_events__class_FK1
-- FOREIGN KEY: class_events__users_FK2
--
ALTER TABLE class_events ADD CONSTRAINT class_events__class_FK1
    FOREIGN KEY (class_id)
    REFERENCES class (class_id);

ALTER TABLE class_events ADD CONSTRAINT class_events__users_FK2
    FOREIGN KEY (insert_user_id)
    REFERENCES users (user_id);
-- ---------------------------------------------------------------------------------
-- ACMAPP-147: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION