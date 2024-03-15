package edu.itmo.isbd.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends HttpException{
	public EntityNotFoundException(String s) {
		super(s, HttpStatus.NOT_FOUND);
	}
}
