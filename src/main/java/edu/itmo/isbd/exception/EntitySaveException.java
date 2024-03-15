package edu.itmo.isbd.exception;

import org.springframework.http.HttpStatus;

public class EntitySaveException extends HttpException{

	public EntitySaveException(String msg, HttpStatus errorStatus) {
		super(msg, errorStatus);
	}
}
