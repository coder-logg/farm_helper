package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.repository.DriverRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;

//	@PostMapping(value = "/registration", produces = "application/json")
//	public ResponseEntity<User> registration(@RequestBody User user) throws URISyntaxException {
////		log.info("new user: " + user);
//		if (!ObjectUtils.allNotNull(user.getLogin(), user.getPassword(), user.getPhone(), user.getMail(), user.getROLE()))
//			throw new HttpException("Incomplete user data was given.", HttpStatus.BAD_REQUEST);
////		User dbUser = userService.registration(user);
//		return ResponseEntity.created(new URI("/user/" + user.getLogin())).body(dbUser);
//	}
}
