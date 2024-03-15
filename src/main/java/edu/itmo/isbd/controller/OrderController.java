package edu.itmo.isbd.controller;

import edu.itmo.isbd.Utils;
import edu.itmo.isbd.dto.OrderDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	@RolesAllowed({"ADMIN"})
	public ResponseEntity<List<OrderDto>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
												 @RequestParam(defaultValue = "10") Integer pageSize,
												 @RequestParam(defaultValue = "name") String sortBy){
		return ResponseEntity.ok(orderService.getAll(pageNo, pageSize, sortBy)
				.stream().map(OrderDto::new).collect(Collectors.toList()));
	}


	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable int id, Authentication auth) {
		Order orderFromDb = orderService.get(id);
		if (!(auth.getName().equals(orderFromDb.getFarmer().getLogin()) && Utils.getRoles(auth).contains("ROLE_FARMER")))
			throw new HttpException("You don't have access for requested data", HttpStatus.FORBIDDEN);
		return ResponseEntity.ok(new OrderDto(orderFromDb));
	}

	@PostMapping
	@PreAuthorize("(hasRole('FARMER') and authentication.name == #order.farmerLogin) or hasRole('ADMIN')")
	public ResponseEntity<OrderDto> postOrder(@RequestBody @P("order") OrderDto orderDto) throws URISyntaxException {
		Order orderFromDb = orderService.saveOrder(orderDto);
		return ResponseEntity.created(new URI("/orders/" + orderFromDb.getId())).build();
	}

	@PutMapping
	@PreAuthorize("(hasRole('FARMER') and authentication.name == #order.farmerLogin) or hasRole('ADMIN')")
	public ResponseEntity<OrderDto> updateOrder(@RequestBody @P("order") OrderDto orderDto) throws URISyntaxException {
		Order orderFromDb = orderService.updateOrder(orderDto);
		return ResponseEntity.ok(new OrderDto(orderFromDb));
	}
}