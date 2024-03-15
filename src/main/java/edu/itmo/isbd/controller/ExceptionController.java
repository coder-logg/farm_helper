package edu.itmo.isbd.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import edu.itmo.isbd.exception.HttpException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(HttpException.class)
	public void httpExceptionHandler(HttpException exc, final HttpServletResponse response) throws IOException {
		response.sendError(exc.getErrorStatus().value(), exc.getMessage());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public void usernameNotFoundExceptionHandler(UsernameNotFoundException exc, final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), exc.getMessage());
	}

	@ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class, JsonMappingException.class})
	public void sqlConstraintsExceptionsHandler(Exception exc, final HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exc.getMessage());
	}

}
