package com.acm.app.classroom.client.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Instructor object
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
@JsonInclude(Include.NON_NULL)
public class Instructor {

    @ApiModelProperty("Associated Class Identifier")
    private int classId;

    @ApiModelProperty("First name of the user.")
    private String firstName;

    @ApiModelProperty("Last name of the user.")
    private String lastName;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}