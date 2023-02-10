package edu.itmo.isbd.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException{

	protected HttpStatus errorStatus;

	public HttpException(String s, HttpStatus errorStatus) {
		super(s);
		this.errorStatus = errorStatus;
	}

	public HttpException(String s) {
		super(s);
	}

	public HttpStatus getErrorStatus() {
		return errorStatus;
	}

	public HttpException setErrorStatus(HttpStatus errorStatus) {
		this.errorStatus = errorStatus;
		return this;
	}
}
