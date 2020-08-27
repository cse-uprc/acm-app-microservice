package com.acm.app.classroom.client.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class Session {

    @ApiModelProperty("Associated Class Identifier")
    private int classId;

    @ApiModelProperty("When the class starts")
    private String startTime;

    @ApiModelProperty("When the class ends.")
    private String endTime;

    @ApiModelProperty("Days of the week the class meets.")
    private Set<Days> meetingDays;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Set<Days> getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(Set<Days> meetingDays) {
        this.meetingDays = meetingDays;
    }
}