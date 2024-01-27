package edu.itmo.isbd.controller;


import edu.itmo.isbd.model.Driver;
import edu.itmo.isbd.model.Review;
import edu.itmo.isbd.model.Role;
import edu.itmo.isbd.model.User;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.ReviewService;
import edu.itmo.isbd.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/")
public class UserController {
	private final UserService userService;
	private final DriverService driverService;
	private final ReviewService reviewService;

	@PostMapping(value = "/registration", produces = "application/json")
	public void registration(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws ServletException, IOException {
		request.getRequestDispatcher("/" + user.getROLE().name().toLowerCase() + "/registration" ).forward(request, response);
	}

	@GetMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response, Principal principal) throws ServletException, IOException {
		Role role = userService.getUserRole(principal.getName());
		request.getRequestDispatcher("/" + role.name().toLowerCase() + "/login").forward(request, response);
	}

	@DeleteMapping("/user/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@RequestParam String username) {
			userService.deleteUser(username);
			return ResponseEntity.ok("User was deleted");
	}

	@GetMapping("/{login}/reviews/my")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Review>> getAllMyReviews(@PathVariable String login){
		return ResponseEntity.ok(reviewService.getAllBySenderLogin(login));
	}

	@GetMapping("/{login}/reviews/for-me")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Review>> getAllReviewsForMe(@PathVariable String login){
		return ResponseEntity.ok(reviewService.getAllByRecipientLogin(login));
	}

	@GetMapping("/drivers")
	public ResponseEntity<List<Driver>> getAllDrivers(@RequestParam(defaultValue = "0") Integer pageNo,
													  @RequestParam(defaultValue = "10") Integer pageSize,
													  @RequestParam(defaultValue = "login") String sortBy) {
		return ResponseEntity.ok(driverService.getAllDrivers(pageNo, pageSize, sortBy));
	}
}
