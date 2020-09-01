-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- Script: 1.0.0.7__Modify_Classroom_Tables.sql
-- Author: Sam Butler
-- Date: 2020-08-27
-- Issue: ACMAPP-159: Modify Classroom Tables
-- Version: v1.0.0
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- ---------------------------------------------------------------------------------
-- ACMAPP-159: START
-- ---------------------------------------------------------------------------------

--
-- Adding session_id and session_type columns
--
ALTER TABLE class_session 
ADD COLUMN session_id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
ADD COLUMN session_type VARCHAR(45) NOT NULL DEFAULT 'LECTURE' AFTER class_id,
ADD PRIMARY KEY (session_id);

--
-- Adding instructor_id
--
ALTER TABLE class_instructors 
ADD COLUMN instructor_id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (instructor_id);
;

-- ---------------------------------------------------------------------------------
-- ACMAPP-159: END
-- ---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OF SCRIPT VERSION
