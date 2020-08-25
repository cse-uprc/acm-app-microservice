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

CREATE TABLE app_permissions (
	permission_id INT NOT NULL,
    web_role_id INT,
    app_id INT,
    insert_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(permission_id),
    FOREIGN KEY(web_role_id) REFERENCES web_roles(web_role_id),
    FOREIGN KEY(app_id) REFERENCES applications(app_id)
);

-- ---------------------------------------------------------------------------------
-- ACMAPP-100: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION