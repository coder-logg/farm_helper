package edu.itmo.isbd.controller;

import edu.itmo.isbd.dto.DeliveryOrderDto;
import edu.itmo.isbd.dto.FarmerDto;
import edu.itmo.isbd.dto.RegistrationDto;
import edu.itmo.isbd.model.*;
import edu.itmo.isbd.service.CustomerService;
import edu.itmo.isbd.service.FarmerService;
import edu.itmo.isbd.service.DeliveryOrderService;
import edu.itmo.isbd.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/farmer")
public class FarmerController {
	@Autowired
	private FarmerService farmerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private DeliveryOrderService deliveryOrderService;

	@GetMapping("/login")
	public ResponseEntity<FarmerDto> getFarmer(Principal principal){
		return ResponseEntity.ok(new FarmerDto(farmerService.getFarmerOrThrow(principal.getName())));
	}

	@PostMapping(value = "/registration", produces = "application/json")
	public ResponseEntity<FarmerDto> register(@RequestBody RegistrationDto farmer) throws URISyntaxException {
		return ResponseEntity.created(new URI("/farmer/login"))
				.body(new FarmerDto(farmerService.saveFarmerOrThrow(new Farmer(farmer.getLogin(), farmer.getPhone(), farmer.getMail(), farmer.getPassword()))));
	}

	@GetMapping("/{login}/orders")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<Order>> getOrders(@PathVariable String login) {
		return ResponseEntity.ok(orderService.getOrdersByFarmerLogin(login));
	}

	@PostMapping("/{login}/orders")
	@PreAuthorize("#login == authentication.name")
	public void createOrder(@PathVariable String login, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/orders" ).forward(request, response);
	}

	@PutMapping("/{login}/orders")
	@PreAuthorize("#login == authentication.name")
	public void updateOrder(@PathVariable String login, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/orders" ).forward(request, response);
	}

	@GetMapping("/{login}/farm")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<Farm> getFarm(@PathVariable @P("login") String login) {
		return ResponseEntity.ok(farmerService.getFarmByFarmerLogin(login));
	}

	@PutMapping("/{login}/farm")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<?> setFarm(@PathVariable @P("login") String login, @RequestBody Farm farm) throws URISyntaxException {
		return ResponseEntity
				.created(new URI("/farms/" + farmerService.createOrUpdateFarm(login, farm).getId()))
				.build();
	}

	@PostMapping("/{login}/delivery-orders")
	@PreAuthorize("#login == authentication.name")
	public void createDeliveryOrder(@PathVariable String login, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/delivery-orders").forward(request, response);
	}

	@GetMapping("/{login}/delivery-orders")
	@PreAuthorize("#login == authentication.name")
	public ResponseEntity<List<DeliveryOrderDto>> getDeliveryOrders(@PathVariable String login) {
		return ResponseEntity.ok(deliveryOrderService.getAllByFarmerLogin(login)
				.stream().map(DeliveryOrderDto::new).collect(Collectors.toList()));
	}

}
