package com.training.task.exception;

public class BadRequestException extends RuntimeException {
	
	public BadRequestException(String message) {
        super(message);
    }

}
