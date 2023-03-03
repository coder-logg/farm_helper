package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Car;
import edu.itmo.isbd.entity.Driver;
import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.service.DriverService;
import edu.itmo.isbd.service.OrderForDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@Autowired
	private OrderForDriveService orderForDriveService;

	@GetMapping("/login")
	public ResponseEntity<Driver> getFarmer(Principal principal) {
		return ResponseEntity.ok(driverService.getOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Driver> register(@RequestBody Driver driver) throws URISyntaxException {
		return ResponseEntity.created(new URI("/driver/login")).body(driverService.saveOrThrow(driver));
	}

	@GetMapping("/{login}/orders-for-drive")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<OrderForDrive>> getOrdersForDrive(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(orderForDriveService.getAllByDriverLogin(login));
	}

	@GetMapping("/{login}/car")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<Car> getCar(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(driverService.getOrThrow(login).getCar());
	}

	@PutMapping("/{login}/car")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<?> setCarForDriver(@PathVariable @P("login") String login, @RequestBody Car car) throws URISyntaxException {
		return ResponseEntity
				.created(new URI("/cars/" + driverService.createOrUpdateCar(login, car).getId()))
				.build();
	}
}
