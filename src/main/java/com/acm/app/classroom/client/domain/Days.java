package com.acm.app.classroom.client.domain;

public enum Days {
    MON("Monday"), TUE("Tuesday"), WED("Wednesday"), THU("Thursday"), FRI("Friday"), SAT("Saturday"), SUN("Sunday");

    private String text;

    Days(String type) {
        this.text = type;
    }

    public String text() {
        return text;
    }
}