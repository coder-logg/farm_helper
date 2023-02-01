package edu.itmo.isbd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User with this login already registered")
public class UserAlreadyRegisteredException extends Exception{
	private String msg;

	public UserAlreadyRegisteredException(String msg) {
		super(msg);
	}
}
