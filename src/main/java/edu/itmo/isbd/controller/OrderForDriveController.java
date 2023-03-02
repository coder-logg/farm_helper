package edu.itmo.isbd.controller;

import edu.itmo.isbd.Utils;
import edu.itmo.isbd.entity.OrderForDrive;
import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.exception.HttpException;
import edu.itmo.isbd.service.OrderForDriveService;
import edu.itmo.isbd.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/orders-for-drive")
public class OrderForDriveController {
	@Autowired
	private OrderForDriveService orderForDriveService;

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('FARMER')")
	public ResponseEntity<OrderForDrive> getOrderForDrive(@PathVariable Integer id, Authentication auth) {
		OrderForDrive orderForDrive = orderForDriveService.get(id);
		User user = (User) auth.getPrincipal();
		if (user.getLogin().equals(orderForDrive.getDriverLogin())
				|| user.getLogin().equals(orderForDrive.getFarmerLogin())
				|| user.getROLE().equals(UserService.Role.ADMIN))
			return ResponseEntity.ok(orderForDrive);
		else throw new HttpException("You don't have access for requested data.", HttpStatus.FORBIDDEN);
	}

	@PostMapping("/add-order")
	@PreAuthorize("(hasRole('FARMER') and authentication.name.equals(orderForDrive.farmerLogin)) or hasRole('ADMIN')")
	public ResponseEntity<OrderForDrive> addOrderForDrive(@RequestBody OrderForDrive orderForDrive) throws URISyntaxException {
		if (!ObjectUtils.allNotNull(orderForDrive.getFarmerLogin(), orderForDrive.getOrderId()))
			throw new HttpException("Incomplete data was given. Required fields: farmerLogin, orderId.", HttpStatus.UNPROCESSABLE_ENTITY);
		OrderForDrive orderForDriveFromDb = orderForDriveService.saveOrThrow(orderForDrive);
		return ResponseEntity
				.created(new URI("/orders-for-drive/" + orderForDrive.getId()))
				.body(orderForDriveFromDb);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'FARMER')")
	@DeleteMapping("/drop/{id}")
	public ResponseEntity<String> deleteOrderForDrive(@PathVariable Integer id, Authentication auth){
		OrderForDrive orderForDriveFromDb = orderForDriveService.get(id);
		List<String> roles = Utils.getRoles(auth);
		if (!(roles.contains("ROLE_ADMIN")
				|| (auth.getName().equals(orderForDriveFromDb.getFarmerLogin()) && roles.contains("ROLE_FARMER"))))
			throw new HttpException("You don't have access to delete order for drive with id = " + id, HttpStatus.FORBIDDEN);
		orderForDriveService.removeOrThrow(id);
		return ResponseEntity.ok("Entity deleted");
	}
}
