package com.acm.service.exceptions;

public class UserNotFoundException extends Exception
{
    private static final long serialVersionUID = -1L;
    public UserNotFoundException(String message, int id){
        super("User not found at:"+ id);
    }
}
