package com.acm.app.classroom.client.domain;

public enum Campus {
    RIDGE("Ridge Campus"), MAIN("LCCC Main Campus");

    private String text;

    Campus(String type) {
        this.text = type;
    }

    public String text() {
        return text;
    }
}