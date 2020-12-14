package com.acm.websockets.client.domain;

public enum WebnotificationType {
    MESSAGE("message"), TEMP("temperature"), ANNOUNCEMENT("announcement");

    private String text;

    WebnotificationType(String type) {
        this.text = type;
    }

    public String text() {
        return text;
    }
}
