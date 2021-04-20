package com.init.productes.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	private final String message;
	private final Throwable throwable; 
	private final HttpStatus status;
	private ZonedDateTime timestamp;
	
	public ApiException(String message, Throwable throwable, HttpStatus status, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.status = status;
		this.timestamp = timestamp;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	
	

}
