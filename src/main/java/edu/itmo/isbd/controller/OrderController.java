package edu.itmo.isbd.controller;

import edu.itmo.isbd.Utils;
import edu.itmo.isbd.model.Car;
import edu.itmo.isbd.model.Order;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	@RolesAllowed({"ADMIN"})
	public ResponseEntity<List<Order>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
											  @RequestParam(defaultValue = "10") Integer pageSize,
											  @RequestParam(defaultValue = "name") String sortBy){
		return ResponseEntity.ok(orderService.getAll(pageNo, pageSize, sortBy));
	}

	@GetMapping("/{id}/{field}")
	public ResponseEntity<?> get(@PathVariable Integer id, @PathVariable String field){
		try {
			return ResponseEntity.ok(Order.class.getDeclaredField(field).get(orderService.get(id)));
		} catch (NoSuchFieldException e) {
			return ResponseEntity.badRequest().body("Order hasn't field '" + field + "'");
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable int id, Authentication auth) {
		Order orderFromDb = orderService.get(id);
		List<String> roles = Utils.getRoles(auth);
		if (!(auth.getName().equals(orderFromDb.getFarmerLogin()) && roles.contains("ROLE_FARMER")))
			throw new HttpException("You don't have access for requested data", HttpStatus.FORBIDDEN);
		return ResponseEntity.ok(orderFromDb);
	}

	@PostMapping
	@PreAuthorize("(hasRole('FARMER') and authentication.name == #order.farmer.login) or hasRole('ADMIN')")
	public ResponseEntity<Order> postOrder(@RequestBody @P("order") Order order) throws URISyntaxException {
		Order orderFromDb = orderService.saveOrder(order);
		return ResponseEntity.created(new URI("/orders/" + orderFromDb.getId())).build();
	}
}
