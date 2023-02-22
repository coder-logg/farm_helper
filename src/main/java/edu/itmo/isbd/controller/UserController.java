package edu.itmo.isbd.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public void registration(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws ServletException, IOException {
		request.getRequestDispatcher("/" + user.getROLE().name().toLowerCase() + "/registration" ).forward(request, response);
	}

	@GetMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response, Principal principal) throws ServletException, IOException {
		UserService.Role role = userService.getUserRole(principal.getName());
		request.getRequestDispatcher("/" + role.name().toLowerCase() + "/login").forward(request, response);
	}

	@DeleteMapping("/test/user/delete")
	public ResponseEntity<?> deleteUser(@RequestParam String username) {
		userService.deleteUser(username);
		return ResponseEntity.ok("User was deleted");
	}

	@GetMapping(value = "/test")
	public ResponseEntity<User> test(){
		User user = userService.getRandomUser();
		log.info(user.toString());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
