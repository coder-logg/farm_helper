package edu.itmo.isbd.controller;


import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

	@DeleteMapping("/user/delete")
	public ResponseEntity<?> deleteUser(@RequestParam String username, Authentication authentication) {
		if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(x -> x.equals("ADMIN"))) {
			userService.deleteUser(username);
			return ResponseEntity.ok("User was deleted");
		} else throw new HttpException("Access to user deleting have only administrator", HttpStatus.FORBIDDEN);
	}
}
