package edu.itmo.isbd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This username already busy.")
public class UserAlreadyRegisteredException extends HttpException{
	private String msg;

	public UserAlreadyRegisteredException(String msg) {
		super(msg);
	}

	public UserAlreadyRegisteredException(String s, HttpStatus errorStatus) {
		super(s, errorStatus);
	}
}
