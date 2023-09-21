package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.*;
import edu.itmo.isbd.service.CustomerService;
import edu.itmo.isbd.service.FarmerService;
import edu.itmo.isbd.service.OrderForDriveService;
import edu.itmo.isbd.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/farmer")
public class FarmerController {
	@Autowired
	private FarmerService farmerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderForDriveService orderForDriveService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/login")
	public ResponseEntity<Farmer> getFarmer(Principal principal){
		return ResponseEntity.ok(farmerService.getFarmerOrThrow(principal.getName()));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<Farmer> register(@RequestBody Farmer farmer) throws URISyntaxException {
		return ResponseEntity.created(new URI("/farmer/login")).body(farmerService.saveFarmerOrThrow(farmer));
	}

	@GetMapping("/{login}/orders")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Order>> getOrders(@PathVariable String login) {
		return ResponseEntity.ok(orderService.getOrdersByFarmerLogin(login));
	}

	@GetMapping("/{login}/orders-for-drive")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<OrderForDrive>> getOrdersForDrive(@PathVariable String login) {
		return ResponseEntity.ok(orderForDriveService.getAllByFarmerLogin(login));
	}

//	@GetMapping("/{login}/customers")
//	@PreAuthorize("#login == authentication.name")
//	public ResponseEntity<List<Customer>> getFarmerCustomers(@PathVariable String login) {
//		return ResponseEntity.ok(customerService.getAllByCustomers(login));
//	}

	@GetMapping("/{login}/farm")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<Farm> getFarm(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(farmerService.getFarmByFarmerLogin(login));
	}

	@PutMapping("/{login}/farm")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<?> setCarForDriver(@PathVariable @P("login") String login, @RequestBody Farm farm) throws URISyntaxException {
		return ResponseEntity
				.created(new URI("/cars/" + farmerService.createOrUpdateFarm(login, farm).getId()))
				.build();
	}

}
