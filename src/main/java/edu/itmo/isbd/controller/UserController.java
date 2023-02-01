package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.service.UserService;
import org.hibernate.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/registration")
	public ResponseEntity registration(@RequestBody User user) throws UserAlreadyRegisteredException, URISyntaxException {
		userService.registration(user);
		return ResponseEntity.created(new URI("/user/" + user.getLogin())).body(user);
	}

	@ExceptionHandler({UserAlreadyRegisteredException.class})
	public ResponseEntity exceptions(){

	}
}
