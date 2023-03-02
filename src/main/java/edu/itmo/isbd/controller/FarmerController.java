package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.entity.Order;
import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.service.FarmerService;
import edu.itmo.isbd.service.OrderForDriveService;
import edu.itmo.isbd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/farmer")
public class FarmerController {
	@Autowired
	private FarmerService farmerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderForDriveService orderForDriveService;

	@GetMapping("/login")
	public ResponseEntity<Farmer> getFarmer(Principal principal){
		return ResponseEntity.ok(farmerService.getFarmerOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Farmer> register(@RequestBody Farmer farmer) throws URISyntaxException {
		return ResponseEntity.created(new URI("/farmer/login")).body(farmerService.saveFarmerOrThrow(farmer));
	}

	@GetMapping("/{login}/orders")
	@PreAuthorize("login.equals(authentication.name)")
	public ResponseEntity<List<Order>> getOrders(@PathVariable String login) {
		return ResponseEntity.ok(orderService.getOrdersByFarmerLogin(login));
	}

	@GetMapping("/{login}/orders-for-drive")
	@PreAuthorize("login.equals(authentication.name)")
	public ResponseEntity<List<OrderForDrive>> getOrdersForDrive(@PathVariable String login) {
		return ResponseEntity.ok(orderForDriveService.getAllByFarmerLogin(login));
	}

	@GetMapping("/{login}/customers")
	@PreAuthorize("login.equals(authentication.name)")
	public ResponseEntity<List<OrderForDrive>> getFarmerCustomers(@PathVariable String login) {
		return ResponseEntity.ok(orderForDriveService.getAllByFarmerLogin(login));
	}


}
