package com.acm.app.classroom.client.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * Classroom object
 *
 * @author Sam Butler
 * @since August 26, 2020
 */
@JsonInclude(Include.NON_NULL)
public class Classroom {

    @ApiModelProperty("Unique Identifier for the classroom.")
    private int id;

    @ApiModelProperty("Name of the class.")
    private String name;

    @ApiModelProperty("How many people are in the class.")
    private int occupantCount;

    @ApiModelProperty("What campus the class is on.")
    private Campus campusType;

    @ApiModelProperty("What the room number of the class is.")
    private int roomId;

    @ApiModelProperty("Who created the classroom.")
    private int insertUserId;

    @ApiModelProperty("When the classroom was created.")
    private Date insertDate;

    @ApiModelProperty("User information of the instructor.")
    private Instructor instructor;

    @ApiModelProperty("Time the class takes place")
    private Session session;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccupantCount() {
        return occupantCount;
    }

    public void setOccupantCount(int occupantCount) {
        this.occupantCount = occupantCount;
    }

    public Campus getCampusType() {
        return campusType;
    }

    public void setCampusType(Campus campusType) {
        this.campusType = campusType;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(int insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}