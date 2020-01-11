package com.firebase.demo.exception;

public class DuplicateRecordException extends Exception {

	private static final long serialVersionUID = -6426879286961984414L;

	public DuplicateRecordException() {
	}

	public DuplicateRecordException(String message) {
		super(message);
	}

}
