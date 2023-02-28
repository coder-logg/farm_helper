package edu.itmo.isbd.controller;

import edu.itmo.isbd.entity.Order;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable int id, Authentication auth) {
		Order orderFromDb = orderService.get(id);
		if (!checkAccess(auth, orderFromDb.getFarmer().getLogin()))
			throw new HttpException("You don't have access for requested data", HttpStatus.FORBIDDEN);
		return ResponseEntity.ok(orderFromDb);
	}

	@PostMapping("/add")
	public ResponseEntity<Order> postOrder(@RequestBody Order order, Authentication authentication){
		if (!checkAccess(authentication, order.getFarmer().getLogin()))
			throw new HttpException("You don't have rights for posting given order", HttpStatus.FORBIDDEN);
		Order orderFromDb = orderService.saveOrder(order);
		return ResponseEntity.ok(orderFromDb);
	}

	private boolean checkAccess(Authentication auth, String farmerLogin){
		List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return (roles.contains("ADMIN")
				|| (auth.getName().equals(farmerLogin)
				&& roles.contains("FARMER")));
	}
}
