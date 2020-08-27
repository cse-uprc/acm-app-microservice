-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.1__CreateAppPermissionsTable.sql
-- Author: Josue Van Dyke
-- Date: 2020-08-07
-- Issue: ACMAPP-100: Create App Permissions Table
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-100: START
-- ---------------------------------------------------------------------------------

--
-- TABLE: app_permissions
--

CREATE TABLE app_permissions (
	permission_id INT      UNSIGNED NOT NULL AUTO_INCREMENT,
    web_role_id   INT      UNSIGNED NOT NULL,
    app_id        INT      UNSIGNED NOT NULL,
    insert_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(permission_id)
)ENGINE=InnoDB;

--
-- INDEX: app_permissions_IDX1
-- INDEX: app_permissions_IDX2
--

CREATE INDEX app_permissions_IDX1 ON app_permissions(app_id);
CREATE INDEX app_permissions_IDX2 ON app_permissions(web_role_id);

--
-- FOREIGN KEY: app_permissions__applications_FK1
-- FOREIGN KEY: app_permissions__applications_FK2
--

ALTER TABLE app_permissions ADD CONSTRAINT app_permissions__applications_FK1
	FOREIGN KEY(app_id)
    REFERENCES applications(app_id);
        
ALTER TABLE app_permissions ADD CONSTRAINT app_permissions__web_roles_FK2
	FOREIGN KEY(web_role_id)
    REFERENCES web_roles(web_role_id);

-- ---------------------------------------------------------------------------------
-- ACMAPP-100: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION