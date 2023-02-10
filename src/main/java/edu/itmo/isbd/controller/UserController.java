package edu.itmo.isbd.controller;


import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public ResponseEntity<User> registration(@RequestBody User user) throws URISyntaxException {
		log.info("new user: " + user);
		if (!ObjectUtils.allNotNull(user.getLogin(), user.getPassword(), user.getPhone(), user.getMail(), user.getROLE()))
			throw new HttpException("Incomplete user data was given.", HttpStatus.BAD_REQUEST);
		User dbUser = userService.registration(user);
		log.info("new user was registered: {}", dbUser);
		return ResponseEntity.created(new URI("/" + dbUser.getROLE().name().toLowerCase() + "/" + dbUser.getLogin())).body(dbUser);
	}

	@GetMapping(value = "/login")
	public ResponseEntity<?> login(Principal principal) throws URISyntaxException {
		UserService.Role role = userService.getUserRole(principal.getName());
		return ResponseEntity.created(new URI("/" + role.name().toLowerCase() + "/" + principal.getName())).build();
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

	@ExceptionHandler({HttpException.class})
	public void exceptions(HttpException exc, final HttpServletResponse response) throws IOException {
		response.sendError(exc.getErrorStatus().value(), exc.getMessage());
	}
}
