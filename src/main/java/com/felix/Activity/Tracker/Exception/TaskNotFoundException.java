package com.felix.Activity.Tracker.Exception;

public class TaskNotFoundException extends   RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}
