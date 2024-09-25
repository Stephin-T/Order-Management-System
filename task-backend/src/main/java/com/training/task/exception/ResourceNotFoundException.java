package com.training.task.exception;



public class ResourceNotFoundException extends RuntimeException {
	
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
