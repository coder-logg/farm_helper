package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.OrderForDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private OrderForDriveService orderForDriveService;

	@GetMapping("/login")
	public ResponseEntity<Driver> getFarmer(Principal principal){
		return ResponseEntity.ok(driverService.getDriverOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Driver> registerAdmin(@RequestBody Driver driver) throws URISyntaxException {
		return ResponseEntity.created(new URI("/driver/login")).body(driverService.saveDriverOrThrow(driver));
	}


}
