package com.acm.service.globals.enums;

public enum WebRole
{
    ADMIN ("Admin"),
    USER ("User");
    private String type;

    WebRole(String type)
    {
        this.type = type;
    }

    public String text()
    {
        return type;
    }
}