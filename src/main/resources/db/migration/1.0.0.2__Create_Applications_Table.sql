-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.2__CreateApplicationsTable.sql
-- Author: Josue Van Dyke
-- Date: 2020-08-07
-- Issue: ACMAPP-101: Create Applications Table
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-101: START
-- ---------------------------------------------------------------------------------

CREATE TABLE applications (
	app_id        INT          UNSIGNED NOT NULL AUTO_INCREMENT,
    app_text_id   VARCHAR(45)  NOT NULL,
    dark_featured INT          UNSIGNED NOT NULL,
    maintenance   INT          UNSIGNED NOT NULL,
    insert_date   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(app_id)
)ENGINE=InnoDB;

-- ---------------------------------------------------------------------------------
-- ACMAPP-101: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION