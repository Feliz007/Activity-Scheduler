package com.felix.Activity.Tracker.Exception;

public class UserNotFoundException  extends   RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
