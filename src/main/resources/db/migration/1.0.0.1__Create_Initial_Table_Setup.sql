-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.1__Create_Initial_Table_Setup.sql
-- Author: Sam Butler
-- Date: 2020-07-02
-- Issue: ACMAPP-1: Create Needed tables to start database
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-1: START
-- ---------------------------------------------------------------------------------

--
-- TABLE: dim_user
--
CREATE TABLE dim_user (
  dim_user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name  VARCHAR(45)  NOT NULL,
  last_name   VARCHAR(45)  NOT NULL,
  email       VARCHAR(45)  NOT NULL,
  insert_date DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (dim_user_id)
)ENGINE=InnoDB;


--
-- TABLE: web_roles
--
CREATE TABLE web_roles (
  web_role_id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
  web_role_text_id VARCHAR(45)  NOT NULL,
  PRIMARY KEY (web_role_id,web_role_text_id)
)ENGINE=InnoDB;


--
-- TABLE: users
--
CREATE TABLE users (
  user_id     INT         UNSIGNED NOT NULL AUTO_INCREMENT,
  email       VARCHAR(45) NOT NULL,
  first_name  VARCHAR(45) NOT NULL,
  last_name   VARCHAR(45) NOT NULL,
  active      INT         UNSIGNED NOT NULL DEFAULT '1',
  web_role_id INT         UNSIGNED NOT NULL DEFAULT '2',
  insert_date DATETIME    NOT NULL          DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
)ENGINE=InnoDB;

--
-- INDEX: users__web_roles_IDX1
--
CREATE INDEX users__web_roles_IDX1 ON users(web_role_id);

--
-- FOREIGN KEY: users__web_roles_FK1
--
ALTER TABLE users ADD CONSTRAINT users__web_roles_FK1
    FOREIGN KEY (web_role_id)
    REFERENCES web_roles (web_role_id);


--
-- TABLE: user_credentials
--
CREATE TABLE user_credentials (
  user_id  INT UNSIGNED NOT NULL,
  username VARCHAR(45)  NOT NULL,
  password VARCHAR(128) NOT NULL
)ENGINE=InnoDB;

--
-- INDEX: user_credentials__users_IDX1
--
CREATE INDEX user_credentials__users_IDX1 ON user_credentials(user_id);

--
-- FOREIGN KEY: user_credentials__users_FK1
--
ALTER TABLE user_credentials ADD CONSTRAINT user_credentials__users_FK1
    FOREIGN KEY (user_id)
    REFERENCES users (user_id);


-- ---------------------------------------------------------------------------------
-- ACMAPP-1: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION