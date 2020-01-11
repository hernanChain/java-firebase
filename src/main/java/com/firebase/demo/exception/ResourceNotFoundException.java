package com.firebase.demo.exception;

public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = -4640022611138628129L;
	
	public ResourceNotFoundException() {
	}
	
	public ResourceNotFoundException(String message) {
        super(message);
	}

}
